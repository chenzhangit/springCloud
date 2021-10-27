package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.entity.Menu;
import com.cn.entity.User;
import com.cn.entity.UserExt;
import com.cn.mapper.UserMapper;
import com.cn.service.UserService;
import com.cn.util.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

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

    @Override
    public List<Map<String, Object>> mapList() {
        return userMapper.selectMaps(null);
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
        //将redis的数据删除
        redisUtils.del("userList");
    }

    @Override
    public void delUser(Integer id) {
        userMapper.deleteById(id);
        //将redis的数据删除
        redisUtils.del("userList");
    }

}
