package com.cn.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @auther
 * @date 2021-06-11-17:04
 */

@Data
@ToString
public class UserExt extends User {

    //权限信息
    private List<Menu> permissions;

}
