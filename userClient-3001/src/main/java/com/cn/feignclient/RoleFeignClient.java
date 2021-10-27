package com.cn.feignclient;

import com.cn.result.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "role-client",fallback = RoleFeignClientFallback.class)
@Service
public interface RoleFeignClient {

    @GetMapping("/role/list")
    ResultVo list();

}
