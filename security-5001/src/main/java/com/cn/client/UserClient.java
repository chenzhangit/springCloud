package com.cn.client;

import com.cn.entity.UserExt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-client")
public interface UserClient {

    @GetMapping("/user/getUserext")
    public UserExt getUserext(String userName);

}
