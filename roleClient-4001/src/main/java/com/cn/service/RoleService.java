package com.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.entity.Role;
import com.cn.entity.User;

public interface RoleService extends IService<Role> {

    IPage<Role> listPage(int page, int size);

    void delRole(Integer id);

}
