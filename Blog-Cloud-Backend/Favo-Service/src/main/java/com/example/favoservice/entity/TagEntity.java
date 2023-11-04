package com.example.favoservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tag")
public class TagEntity {
    @TableId(type=IdType.AUTO)
    private Integer id;
    @TableField("tag_name")
    private String tagName;
}
