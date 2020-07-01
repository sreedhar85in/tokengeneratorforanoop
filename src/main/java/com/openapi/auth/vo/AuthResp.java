package com.openapi.auth.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthResp {

    private String token;

    private int userId;

    private LocalDateTime expireAt;
}