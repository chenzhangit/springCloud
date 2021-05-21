package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.mapper.UserMapper;
import com.cn.model.HtUser;
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
        LambdaQueryWrapper<HtUser> thUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        thUserLambdaQueryWrapper.eq(HtUser::getName,userName);
        thUserLambdaQueryWrapper.eq(HtUser::getUserPass,password);
        HtUser htUser = userMapper.selectOne(thUserLambdaQueryWrapper);
        if (htUser==null){
            return "no";
        }
        return "yes";
    }

}
