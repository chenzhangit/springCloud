package com.cn.exception;

import com.cn.result.ResultEnum;
import org.springframework.stereotype.Component;

/**
 * @auther
 * @date 2021-06-09-15:10
 */
@Component
public class ExceptionCast {

    public static void cast(ResultEnum resultEnum){
        throw new CustomException(resultEnum);
    }

}
