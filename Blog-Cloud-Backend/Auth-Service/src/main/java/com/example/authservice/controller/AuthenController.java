package com.example.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api/user"))
public class AuthenController {

    /**
     * description: For services call, front-end developer do not have to focus
     * @param:
     * @return:
     */
    @GetMapping("/auth")
    public ResponseEntity auth(){
        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;
    }
}
