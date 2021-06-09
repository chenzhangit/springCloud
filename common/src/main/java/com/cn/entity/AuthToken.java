package com.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther
 * @date 2021-06-09-15:48
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {

    private String accessToken;

    private String refreshToken;

    private String jwtToken;

}
