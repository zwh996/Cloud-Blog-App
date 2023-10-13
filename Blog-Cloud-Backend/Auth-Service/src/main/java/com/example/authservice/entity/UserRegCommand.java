package com.example.authservice.entity;

import lombok.Data;

@Data
public class UserRegCommand {
    private String username;
    private String password;
    private String email;
}
