package com.cn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cn.entity.Role;
import com.cn.producer.MsgProducer;
import com.cn.result.ResultVo;
import com.cn.result.ResultVoUtil;
import com.cn.service.RoleService;
import com.cn.service.impl.RoleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "角色管理相关接口", description = "角色管理相关接口", tags = "角色管理相关接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    RoleService roleService;

    @Resource
    private MsgProducer msgProducer;

    @ApiOperation(value = "删除角色")
    @GetMapping("/delRole")
    public ResultVo delRole(Integer id){
        roleService.delRole(id);
        return ResultVoUtil.success();
    }

    @ApiOperation(value = "角色列表分页",notes = "请求参数{}")
    @GetMapping("/pageList")
    public ResultVo pageList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        IPage<Role> list = roleService.listPage(page, size);
        return ResultVoUtil.success(list);
    }

    @ApiOperation(value = "角色列表",notes = "请求参数{}")
    @GetMapping("/list")
    public ResultVo list(){
        return ResultVoUtil.success(roleService.list());
    }

    @ApiOperation(value = "发送信息")
    @GetMapping("/sendMsg")
    public ResultVo sendMsg(){
        msgProducer.sendMsg("给爷sha！");
        return ResultVoUtil.success();
    }

}
