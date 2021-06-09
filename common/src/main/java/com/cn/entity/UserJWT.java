package com.cn.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @auther
 * @date 2021-06-09-14:46
 */
@Data
@ToString
public class UserJWT extends User{

    private String id;
    private String name;
    private String userpic;
    private String utype;
    private String companyId;

    public UserJWT(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(password,username,authorities);
    }

}
