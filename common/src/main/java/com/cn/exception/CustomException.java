package com.cn.exception;


import com.cn.result.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huangxunhui
 * Date: Created in 2018-12-18 10:25
 * Utils: Intellij Idea
 * Description: 自定义异常
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    /**
     * 状态码
     */
    private final Integer code;


    /**
     * 自定义异常
     *
     * @param resultEnum 返回枚举对象
     */
    public CustomException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    /**
     * @param code    状态码
     * @param message 错误信息
     */
    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
