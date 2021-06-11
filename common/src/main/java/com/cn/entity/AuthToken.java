package com.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @auther
 * @date 2021-06-09-15:48
 */

@Data
@NoArgsConstructor
@ToString
public class AuthToken implements Serializable {

    private String accessToken;

    private String refreshToken;

    private String jwtToken;

}
