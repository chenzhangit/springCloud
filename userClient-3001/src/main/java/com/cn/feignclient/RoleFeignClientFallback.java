package com.cn.feignclient;

import com.cn.result.ResultVo;
import com.cn.result.ResultVoUtil;
import org.springframework.stereotype.Component;

@Component
public class RoleFeignClientFallback implements RoleFeignClient {

    @Override
    public ResultVo list() {
        return ResultVoUtil.error("降级方法");
    }

}
