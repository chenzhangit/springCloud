package com.cn.feignclient;

import org.springframework.stereotype.Component;

@Component
public class RoleFeignClientFallback implements RoleFeignClient {

    @Override
    public String list() {
        return "降级方法";
    }

}
