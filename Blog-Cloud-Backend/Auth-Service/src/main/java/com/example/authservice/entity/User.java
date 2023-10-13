package com.example.authservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_info")
public class User {

    @TableId
    private int id;
    @TableField("user_name")
    private String username;
    private String password;
    private String email;
}
