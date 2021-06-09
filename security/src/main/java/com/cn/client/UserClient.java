package com.cn.client;

import com.cn.entity.User;
import com.cn.result.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user_client")
public interface UserClient {

    @GetMapping("/user/login")
    public String login();

    @GetMapping("/user/getUserext")
    public User getUserext(String userName);

}
