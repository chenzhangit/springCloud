package com.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cz
 * @since 2021-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录账号
     */
    @TableField(value = "login_code")
    private String loginCode;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户姓名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

}
