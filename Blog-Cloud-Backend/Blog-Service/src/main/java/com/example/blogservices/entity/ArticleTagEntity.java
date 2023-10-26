package com.example.blogservices.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("article_tag")
@Data
public class ArticleTagEntity {

    @TableId
    private int id;
    @TableField("article_id")
    private int articleId;
    @TableField("tag_id")
    private int tagId;
}
