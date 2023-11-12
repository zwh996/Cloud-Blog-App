package com.example.authservice;

import com.example.authservice.command.LoginReturnCommand;
import com.example.authservice.entity.UserLogCommand;
import com.example.authservice.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertTrue;
@SpringBootTest
class AuthServiceApplicationTests {

    @Autowired
    LoginService loginService;

    @Test
    void loginTest() {
        UserLogCommand userLogCommand = new UserLogCommand();
        userLogCommand.setUsername("ZWH");
        userLogCommand.setPassword("010726");
        ResponseEntity<LoginReturnCommand> res = loginService.login(userLogCommand);
        assertTrue("log in failed.", res.getBody().getJwt() != null);
    }

}
