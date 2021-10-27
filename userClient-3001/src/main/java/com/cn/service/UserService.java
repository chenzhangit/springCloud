package com.cn.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.entity.User;
import com.cn.entity.UserExt;
import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    UserExt getUserext(String userName);

    List<Map<String, Object>> mapList();

    void addUser(User user);

    void delUser(Integer id);
}
