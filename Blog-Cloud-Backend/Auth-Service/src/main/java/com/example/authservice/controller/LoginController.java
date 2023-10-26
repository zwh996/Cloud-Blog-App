package com.example.authservice.controller;


import com.example.authservice.entity.UserLogCommand;
import com.example.authservice.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class LoginController {


    @Autowired
    LoginService loginService;

    /**
     * description:return map<userid, token>, token is for after login call, should restore it and user it as a http header in following queries
     * @param: command(including username and pwd)
     * @return: map
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLogCommand command) {
        return loginService.login(command);
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
