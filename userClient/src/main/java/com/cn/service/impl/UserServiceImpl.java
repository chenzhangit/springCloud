package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.entity.User;
import com.cn.mapper.UserMapper;
import com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String userName,String password){
        LambdaQueryWrapper<User> thUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        thUserLambdaQueryWrapper.eq(User::getUserName,userName);
        thUserLambdaQueryWrapper.eq(User::getPassword,password);
        User htUser = userMapper.selectOne(thUserLambdaQueryWrapper);
        if (htUser==null){
            return "no";
        }
        return "yes";
    }

}
