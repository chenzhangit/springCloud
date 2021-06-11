package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cn.entity.Menu;
import com.cn.entity.User;
import com.cn.entity.UserExt;
import com.cn.mapper.UserMapper;
import com.cn.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserExt getUserext(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,userName);
        User user = userMapper.selectOne(wrapper);
        if(user == null){
            return null;
        }
        List<Menu> menus = userMapper.selectPermissionByUserId(user.getId());
        UserExt userExt = new UserExt();
        BeanUtils.copyProperties(user,userExt);
        userExt.setPermissions(menus);
        return userExt;
    }

}
