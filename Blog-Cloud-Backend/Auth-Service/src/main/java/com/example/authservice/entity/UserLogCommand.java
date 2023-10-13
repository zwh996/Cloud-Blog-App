package com.example.authservice.entity;

import lombok.Data;

@Data
public class UserLogCommand {
    private String username;
    private String password;
}
