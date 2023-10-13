package com.example.authservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.authservice.entity.User;
import com.example.authservice.entity.UserRegCommand;
import com.example.authservice.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class SignupController {
    @Resource
    UserMapper userMapper;

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    @RequestMapping("register-error")
    public String registerError(Model model){
        model.addAttribute("error",true);
        return "register";
    }

    @RequestMapping("register-save")
    public String registerSave(@RequestBody UserRegCommand userRegCommand,
                               Model model) {
        log.info("register email:{}", userRegCommand.getEmail());
        User sysUser = new User();
        sysUser.setUsername(userRegCommand.getUsername());
        sysUser.setPassword(userRegCommand.getPassword());
        sysUser.setEmail(userRegCommand.getEmail());
        // judge
        if (sysUser.getUsername() == null || sysUser.getPassword() == null) {
            model.addAttribute("error", true);
            return "register";
        }
        QueryWrapper<User> wrapper= new QueryWrapper<>();
        wrapper.eq("user_name", userRegCommand.getUsername());
        User tmp = userMapper.selectOne(wrapper);
        if(tmp != null)
            return "register";
        try {
            // encrypt
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(sysUser.getPassword());
            sysUser.setPassword(password);
            log.info("user info:{}",sysUser);
            // insert
            userMapper.insert(sysUser);
            // redirect
            return "redirect:/login";
        } catch (Exception e) {
            // sign up error
            model.addAttribute("error", true);
            return "register";
        }
    }

}
