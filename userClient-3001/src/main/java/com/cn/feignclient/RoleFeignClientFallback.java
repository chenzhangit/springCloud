package com.cn.feignclient;

import com.cn.result.ResultVo;
import com.cn.result.ResultVoUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class RoleFeignClientFallback implements RoleFeignClient {

    @Override
    public ResultVo list() {
        return ResultVoUtil.error("降级方法");
    }

    @Override
    public ResultVo delRole(Integer id) {
        return ResultVoUtil.error("降级方法");
    }

}
