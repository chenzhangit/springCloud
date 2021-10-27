package com.cn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cn.entity.User;
import com.cn.entity.UserExt;
import com.cn.feignclient.RoleFeignClient;
import com.cn.mapper.UserMapper;
import com.cn.result.ResultVo;
import com.cn.result.ResultVoUtil;
import com.cn.service.UserService;
import com.cn.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;


@Api(value = "用户管理相关接口", description = "用户管理相关接口", tags = "用户管理相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RoleFeignClient roleFeignClient;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @ApiOperation(value = "角色列表")
    @GetMapping("/getRole")
    public ResultVo getRole(){
        return roleFeignClient.list();
    }

    @ApiOperation(value = "根据用户名查询用户信息",notes = "请求参数{userName 用户名 | password  登录密码}")
    @GetMapping("/getUserext")
    public UserExt getUserext(String userName){
        return userService.getUserext(userName);
    }

    @ApiOperation(value = "用户列表")
    @GetMapping("/list")
    public ResultVo list(){
        String userList = redisUtils.get("userList");
        if (userList==null){
            System.out.println("从db读取数据");
            List<User> list = userService.list();
            if (CollectionUtils.isNotEmpty(list)){
                redisUtils.set("userList", JSON.toJSONString(list));
            }
            return ResultVoUtil.success(list);
        }else {
            System.out.println("从缓存读取数据");
            return ResultVoUtil.success(JSONObject.parseArray(userList, User.class));
        }
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    public ResultVo addUser(@RequestBody User user){
        userService.addUser(user);
        return ResultVoUtil.success("新增用户成功");
    }

    @ApiOperation(value = "删除用户")
    @GetMapping("/delUser")
    public ResultVo delUser(Integer id){
        userService.delUser(id);
        return ResultVoUtil.success("删除用户成功");
    }

    @ApiOperation(value = "list<基础类型>测试")
    @GetMapping("/listBaseTest")
    public ResultVo listBaseTest(){
        //        filter：过滤流中的某些元素
        //        limit(n)：获取n个元素
        //        skip(n)：跳过n元素，配合limit(n)可实现分页
        //        distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(52);
        list.add(36);
        list.add(78);
        list.add(110);
        list.add(110);

        list = list.stream().filter(a -> a > 30)
                .distinct()
                .skip(1)
                .limit(2).collect(Collectors.toList());
        //按照成绩正序排序
        list.sort(Comparator.comparing(Integer::intValue));
        //按照成绩倒序排序
        list.sort(Comparator.comparing(Integer::intValue).reversed());
        //去重
        list = list.stream().distinct().collect(Collectors.toList());
        //求和

        return ResultVoUtil.success(list);
    }

    @ApiOperation(value = "list<实体类>测试")
    @GetMapping("/listObjTest")
    public ResultVo listObjTest(){
        List<User> userList = userService.list();

        //去重，必须先实现equals和hashcode方法
        userList = userList.stream().distinct().collect(Collectors.toList());

        //按照成绩正序排序（当成绩为空时报错）
        //userList.sort(Comparator.comparing(User::getScore));
        //按照成绩倒序排序（当成绩为空时报错）
        //userList.sort(Comparator.comparing(User::getScore).reversed());

        //根据id正序排序
        //userList = userList.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
        //按照id倒序排序
        //userList = userList.stream().sorted(Comparator.comparing(User::getId).reversed()).collect(Collectors.toList());

        //按照成绩倒序排序（当成绩为空时正常）
        //userList = userList.stream().sorted(Comparator.comparing(l -> l.getScore(), Comparator.nullsLast(Double::compareTo).reversed())).collect(Collectors.toList());

        //去掉0分的分数，再按照姓名分组，取分数的平均值，再按照平均值排序
/*        userList.stream().filter(a -> a.getScore() != null && a.getScore() != 0).
                collect(Collectors.groupingBy(User::getUsername, Collectors.averagingDouble(User::getScore))).
                entrySet().stream().map(e -> new User(e.getKey(), e.getValue())).collect(Collectors.toList()).
                sort(Comparator.comparing(User::getScore).reversed());*/

        //根据姓名分组，求每个人的总金额
/*        userList = userList.stream()
                .collect(Collectors.groupingBy(User::getUsername,CollectorsUtil.summingBigDecimal(User::getMoney)))
                .entrySet().stream().map(e -> new User(e.getKey(), e.getValue()))
                .collect(Collectors.toList());*/
        //自定义排序 先按照姓名升序，姓名相同则按年龄升序
/*        userList = userList.stream().sorted(
                ((o1, o2) -> {
                    if (o1.getUsername().equals(o2.getUsername())){
                        return o1.getScore().compareTo(o2.getScore());
                    }else {
                        return o1.getUsername().compareTo(o2.getUsername());
                    }
                })
        ).collect(Collectors.toList());

        //遍历集合删除分数小于60的元素
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()){
            User next = iterator.next();
            if (next.getScore()<=60){
                iterator.remove();
            }
        }*/

/*
        boolean a = userList.stream().allMatch(e -> "1".equals(e.getStatus()));     //用户状态每个都等于1
        boolean b = userList.stream().noneMatch(e -> "1".equals(e.getStatus()));    //用户状态每个都不等于1
        boolean c = userList.stream().anyMatch(e -> "1".equals(e.getStatus()));     //有一个满足返回true
        User first = userList.stream().findFirst().get();
        User any = userList.stream().findAny().get();
        BigDecimal bigDecimal = userList.stream().map(User::getMoney).max(BigDecimal::compareTo).get();     //获取集合中钱最多的数额
        userList.sort(Comparator.comparing(User::getUsername).thenComparing(User::getScore));
*/

/*        Map<Integer,BigDecimal> collect = userList.stream().collect(Collectors.toMap(User::getId, User::getMoney));
        userList = collect.entrySet().stream().map(e -> new User(e.getKey(), e.getValue())).collect(Collectors.toList());

        Map<Integer, User> collect = userList.stream().collect(Collectors.toMap(User::getId, a -> a));*/

        //字符串分隔符连接
        String collect = userList.stream().map(User::getUsername).collect(Collectors.joining(",", "(", ")"));
        return ResultVoUtil.success(collect);

    }

    @ApiOperation(value = "list<Map<T,T>>测试")
    @GetMapping("/listMapTest")
    public ResultVo listMapTest(){
        List<Map<String, Object>> userMap = userService.mapList();
        userMap = userMap.stream().sorted(Comparator.comparing(UserController::sortMap)).collect(Collectors.toList());
        return ResultVoUtil.success(userMap);
    }

    private static Double sortMap(Map<String,Object> map){
        return (Double) map.get("score");
    }

}
