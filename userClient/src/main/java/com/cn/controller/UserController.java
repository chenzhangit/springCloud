package com.cn.controller;

import com.cn.entity.User;
import com.cn.feignclient.RoleFeignClient;
import com.cn.result.ResultVo;
import com.cn.result.ResultVoUtil;
import com.cn.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

import static java.lang.Thread.sleep;

@Api(value = "用户管理相关接口", description = "用户管理相关接口", tags = "用户管理相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RoleFeignClient roleFeignClient;

    @Autowired
    private UserService userService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/getRole")
    public ResultVo getRole(){
        String list = roleFeignClient.list();
        return ResultVoUtil.success(list);
    }

    @ApiOperation(value = "用户登录接口",notes = "请求参数{userName 用户名 | password  登录密码}")
    @GetMapping("/login")
    public ResultVo login(String userName,String password){
        return ResultVoUtil.success(userService.login(userName,password));
    }

    @ApiOperation(value = "根据用户名查询用户信息",notes = "请求参数{userName 用户名 | password  登录密码}")
    @GetMapping("/getUserext")
    public User getUserext(String userName){
        return null;
    }

    @GetMapping("/redisTest")
    public String redisTest(){
        Jedis jedis = jedisPool.getResource();
        String lockKey = "lockkey";
        String requestId = UUID.randomUUID().toString();
        if("OK".equals(jedis.set(lockKey,requestId,"NX","PX",60))){
            String list = roleFeignClient.list();
            jedis.set("roleList",list);
            jedis.expire("yes",10);
            jedis.del(lockKey);
        }else {
            try{
                sleep(10);
            }catch (Exception e){
            }
        }
        return jedis.get("roleList");
    }

}
