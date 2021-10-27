package com.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author cz
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 4220515347228129741L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    /**
     * 用户状态
     */
    private String status;

    private Date createTime;

    private Date updateTime;

    private Double score;

    private BigDecimal money;

    public User(Integer id, String username, Double score, BigDecimal money) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.money = money;
    }

    public User(String username, Double score) {
        this.username = username;
        this.score = score;
    }

    public User(String username, BigDecimal money) {
        this.username = username;
        this.money = money;
    }

    public User(Integer id, BigDecimal money) {
        this.id = id;
        this.money = money;
    }

    public User() {

    }

}
