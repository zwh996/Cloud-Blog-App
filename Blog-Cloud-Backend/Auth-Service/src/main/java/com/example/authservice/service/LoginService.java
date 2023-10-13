package com.example.authservice.service;

import com.example.authservice.entity.UserLogCommand;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity login(UserLogCommand command);
}
