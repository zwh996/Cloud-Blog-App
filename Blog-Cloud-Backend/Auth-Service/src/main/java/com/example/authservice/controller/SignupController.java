package com.example.authservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.authservice.entity.User;
import com.example.authservice.entity.UserRegCommand;
import com.example.authservice.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class SignupController {
    @Resource
    UserMapper userMapper;

    @RequestMapping("register-error")
    public String registerError(Model model){
        model.addAttribute("error",true);
        return "register";
    }

    /**
     * description: register interface
     * @param:
     * @return:
     */
    @RequestMapping("register-save")
    public ResponseEntity registerSave(@RequestBody UserRegCommand userRegCommand,
                                       Model model) {
        log.info("register email:{}", userRegCommand.getEmail());
        User sysUser = new User();
        sysUser.setUsername(userRegCommand.getUsername());
        sysUser.setPassword(userRegCommand.getPassword());
        sysUser.setEmail(userRegCommand.getEmail());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",sysUser.getUsername());
        // judge
        if (!Objects.isNull(userMapper.selectOne(queryWrapper))) {
            model.addAttribute("error", true);
            return new ResponseEntity("register attribute error", HttpStatus.CONFLICT);
        }
        try {
            // encrypt
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(sysUser.getPassword());
            sysUser.setPassword(password);
            sysUser.setRegistrationTime(new Date());
            log.info("user info:{}",sysUser);
            // insert
            userMapper.insert(sysUser);
            // redirect
            return new ResponseEntity<>("success register",HttpStatus.OK);
        } catch (Exception e) {
            // sign up error
            model.addAttribute("error", true);
            return new ResponseEntity<>("fail register",HttpStatus.CONFLICT);
        }
    }

}
