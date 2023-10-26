package com.example.commentservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_info")
public class UserEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("user_name")
    private String userName;
    @TableField("user_nickname")
    private String userNickname;
    @TableField("password")
    private String userPassword;
    @TableField("status")
    private Integer userStatus;
    @TableField("email")
    private String userEmail;
    @TableField("user_picture")
    private String userPicture;
    @TableField("user_registetime")
    private Date userRegistetime;
    @TableField("user_signature")
    private String userSignature;
}
