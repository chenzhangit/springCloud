package com.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.entity.Role;
import com.cn.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public IPage<Role> list(int page, int size){
        LambdaQueryWrapper<Role> tbRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbRoleLambdaQueryWrapper.eq(Role::getId,1);
        Page<Role> rolePage = new Page<>(page,size);
        IPage<Role> roleIPage = roleMapper.selectPage(rolePage, tbRoleLambdaQueryWrapper);
        return roleIPage;
    }

}
