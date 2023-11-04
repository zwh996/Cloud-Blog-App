package com.example.authservice.command;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName LoginReturnCommand
 * @Date 2023/10/27 15:54
 * @Author weihuazhang
 * @Version 1.0.0
 **/
@Data
public class LoginReturnCommand {
    int userId;
    String jwt;
    private String userName;
    private String userNickname;
    private String userEmail;
    private Date userRegistetime;
    private String userSignature;
}
