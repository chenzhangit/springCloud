package com.cn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cn.model.TbRole;
import com.cn.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "角色管理相关接口", description = "角色管理相关接口", tags = "角色管理相关接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @ApiOperation(value = "查询所有角色接口",notes = "请求参数{}")
    @GetMapping("/list")
    public IPage<TbRole> list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        return roleService.list(page,size);
    }

}
