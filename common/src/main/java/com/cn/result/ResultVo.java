package com.cn.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @auther
 * @date 2021-05-28-11:36
 */
@Data
@ApiModel("固定返回格式")
public class ResultVo {

    private Integer code;

    private String message;

    private Object data;

}
