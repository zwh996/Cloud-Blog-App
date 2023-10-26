package com.example.userservice.command;

import lombok.Data;

import java.util.Date;

@Data
public class UserCommand {
    private Integer userId;

    private String userName;

    private String userNickname;

    private Integer userStatus;

    private String userEmail;

    private String userPicture;

    private Date userRegistetime;

    private String userSignature;
}
