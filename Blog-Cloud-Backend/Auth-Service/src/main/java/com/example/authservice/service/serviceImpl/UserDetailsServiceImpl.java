package com.example.authservice.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.authservice.entity.LoginUser;
import com.example.authservice.entity.User;
import com.example.authservice.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", username));
        log.info("username:{}", username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found.");
        }

        // 创建UserDetails对象并返回，通常使用User实体类的属性填充
        return new LoginUser(user);
    }
}

