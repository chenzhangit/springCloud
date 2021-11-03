package com.cn.feignclient;

import com.cn.result.ResultVo;
import com.cn.result.ResultVoUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "role-client",fallback = RoleFeignClientFallback.class)
@Service
public interface RoleFeignClient {

    @GetMapping("/role/list")
    ResultVo list();

    @GetMapping("/role/delRole")
    ResultVo delRole(@RequestParam Integer id);

}
