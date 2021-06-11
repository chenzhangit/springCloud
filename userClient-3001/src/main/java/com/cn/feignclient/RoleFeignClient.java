package com.cn.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "role-client",fallback = RoleFeignClientFallback.class)
@Service
public interface RoleFeignClient {

    @RequestMapping("/role/list")
    String list();

}
