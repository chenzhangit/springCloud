package com.cn.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.cn.mapper.RoleMapper;
import com.cn.model.TbRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {

/*    @Resource
    private RoleMapper roleMapper;*/

    @Autowired
    private RoleMapper roleMapper;

    public IPage<TbRole> list(int page, int size){
        LambdaQueryWrapper<TbRole> tbRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbRoleLambdaQueryWrapper.eq(TbRole::getId,1);
        Page<TbRole> rolePage = new Page<>(page,size);
        IPage<TbRole> roleIPage = roleMapper.selectPage(rolePage, tbRoleLambdaQueryWrapper);
        return roleIPage;
    }

}
