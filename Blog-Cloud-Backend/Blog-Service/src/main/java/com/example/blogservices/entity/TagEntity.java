package com.example.blogservices.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tag")
@Data
public class TagEntity {
    @TableId
    int id;
    @TableField("tag_name")
    String tagName;
}
