package com.example.authservice.service.serviceImpl;

import com.example.authservice.entity.LoginUser;
import com.example.authservice.entity.UserLogCommand;
import com.example.authservice.service.LoginService;
import com.example.authservice.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity login(UserLogCommand command) {
        //authentication
        UsernamePasswordAuthenticationToken userInfo = new UsernamePasswordAuthenticationToken(command.getUsername(), command.getPassword());
        Authentication authentication = authenticationManager.authenticate(userInfo);
        if(Objects.isNull(authentication)){
            throw new RuntimeException("login fail");
        }
        LoginUser user = (LoginUser) authentication.getPrincipal();
        int id = user.getUser().getId();
        String jwt = JwtUtil.createJWT(Integer.toString(id));
        Map<Integer,String> map = new HashMap<>();
        map.put(id,jwt);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
