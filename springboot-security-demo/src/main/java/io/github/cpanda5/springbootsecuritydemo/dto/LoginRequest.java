package io.github.cpanda5.springbootsecuritydemo.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}

