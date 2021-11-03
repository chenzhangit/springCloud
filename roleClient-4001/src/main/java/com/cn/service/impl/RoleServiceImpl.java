package com.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.entity.Role;
import com.cn.mapper.RoleMapper;
import com.cn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public IPage<Role> listPage(int page, int size){
        LambdaQueryWrapper<Role> tbRoleLambdaQueryWrapper = Wrappers.lambdaQuery();
        Page<Role> rolePage = new Page<>(page,size);
        IPage<Role> roleIPage = roleMapper.selectPage(rolePage, tbRoleLambdaQueryWrapper);
        return roleIPage;
    }

    @Override
    public void delRole(Integer id){
        roleMapper.deleteById(id);
    }

}
