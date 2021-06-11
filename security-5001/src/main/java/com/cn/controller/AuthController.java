package com.cn.controller;

import com.cn.entity.AuthToken;
import com.cn.entity.User;
import com.cn.exception.ExceptionCast;
import com.cn.result.ResultEnum;
import com.cn.result.ResultVo;
import com.cn.result.ResultVoUtil;
import com.cn.sevice.AuthService;
import com.cn.util.CookieUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @auther
 * @date 2021-06-09-14:40
 */
@Api(value = "登录验证相关接口", description = "登录验证相关接口", tags = "登录验证相关接口")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    AuthService authService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/userlogin")
    public ResultVo login(@RequestBody User user) {
        if(user == null || StringUtils.isEmpty(user.getUsername())){
            ExceptionCast.cast(ResultEnum.FORMAT_ERROR);
        }
        if(user == null || StringUtils.isEmpty(user.getPassword())){
            ExceptionCast.cast(ResultEnum.FORMAT_ERROR);
        }
        //账号
        String username = user.getUsername();
        //密码
        String password = user.getPassword();

        //申请令牌
        AuthToken authToken =  authService.login(username,password,clientId,clientSecret);

        //用户身份令牌
        String accessToken = authToken.getAccessToken();
        //将令牌存储到cookie
        this.saveCookie(accessToken);
        return ResultVoUtil.success(accessToken);
    }

    @ApiOperation(value = "用户退出")
    @PostMapping("/userlogout")
    public ResultVo logout() {
        //取出cookie中的用户身份令牌
        String uid = getTokenFormCookie();
        //删除redis中的token
        boolean result = authService.delToken(uid);
        //清除cookie
        this.clearCookie(uid);
        return ResultVoUtil.success();
    }

    @ApiOperation(value = "获取令牌")
    @GetMapping("/userjwt")
    public ResultVo userjwt() {
        //取出cookie中的用户身份令牌
        String uid = getTokenFormCookie();
        if(uid == null){
            //return new JwtResult(CommonCode.FAIL,null);
            return ResultVoUtil.error("无身份令牌");
        }

        //拿身份令牌从redis中查询jwt令牌
        AuthToken userToken = authService.getUserToken(uid);
        if(userToken!=null){
            //将jwt令牌返回给用户
            String jwtToken = userToken.getJwtToken();
            //return new JwtResult(CommonCode.SUCCESS,jwtToken);
            return ResultVoUtil.success(jwtToken);
        }
        return null;
    }

    //取出cookie中的身份令牌
    private String getTokenFormCookie(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        if(map!=null && map.get("uid")!=null){
            String uid = map.get("uid");
            return uid;
        }
        return null;
    }

    //将令牌存储到cookie
    private void saveCookie(String token){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,cookieMaxAge,false);
    }

    //从cookie删除token
    private void clearCookie(String token){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,0,false);
    }

}
