package com.example.userservice.service;

import com.example.userservice.command.UserCommand;
import com.example.userservice.command.queryCommand.UserQueryCommand;
import com.github.pagehelper.PageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {
    UserCommand getUserByUsername(String userName);

    UserCommand getUserById(Integer userId);

    int nameCheck(String userName);

    int update(UserCommand user);

    List<UserCommand> getList(UserQueryCommand query);

    public ResponseEntity imageUpload(MultipartFile file, int id);
}
