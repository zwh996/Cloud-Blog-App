package com.example.userservice.controller;

import com.example.userservice.command.UserCommand;
import com.example.userservice.command.queryCommand.UserQueryCommand;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/get-user-by-id/{userId}")
    public ResponseEntity getUserById(@PathVariable("userId") int userId) {
        UserCommand command = userService.getUserById(userId);
        if (Objects.isNull(command)) {
            return new ResponseEntity(null, HttpStatus.OK);
        }
        return new ResponseEntity(command, HttpStatus.OK);
    }

    @GetMapping("/get-user-by-name/{name}")
    public ResponseEntity getUserByName(@PathVariable("name") String name) {
        UserCommand command = userService.getUserByUsername(name);
        if (Objects.isNull(command)) {
            return new ResponseEntity(null, HttpStatus.OK);
        }
        return new ResponseEntity(command, HttpStatus.OK);
    }

    /**
     * description: get user by nickname or name, only have to set nickname or name value
     * @param: queryCommand
     * @return: List<UserCommand> in ResponseEntity
     */
    @GetMapping("/get-user-list")
    public ResponseEntity getUserList(@RequestBody UserQueryCommand queryCommand) {
        List<UserCommand> list = userService.getList(queryCommand);
        if (Objects.isNull(list)) {
            return new ResponseEntity(null, HttpStatus.OK);
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    /**
     * description: update user info, only including name, email and userSignature; id remain and compulsory
     * @param: userCommand
     * @return: ResponseEntity
     */
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody UserCommand userCommand) {
        if (userService.update(userCommand) == 0) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * description: check name is not used, do not check nickname
     * @param: name
     * @return: CONFLICT or not(through checking HttpStatus)
     */
    @GetMapping("/name-check/{name}")
    public ResponseEntity nameCheck(@PathVariable("name") String name) {
        if (userService.nameCheck(name) == 0) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/image-upload")
    public ResponseEntity imageUpload(@RequestParam("file") MultipartFile file,@RequestParam("id") int id) {
        return userService.imageUpload(file,id);
    }
}
