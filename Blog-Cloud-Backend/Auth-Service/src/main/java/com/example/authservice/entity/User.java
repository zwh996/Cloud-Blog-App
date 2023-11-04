package com.example.authservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_info")
public class User {

    @TableId
    private int id;
    @TableField("user_name")
    private String username;
    private String password;
    private String email;
    @TableField("user_registetime")
    private Date registrationTime;
    @TableField("user_nickname")
    private String userNickname;
    @TableField("user_signature")
    private String signature;

}
