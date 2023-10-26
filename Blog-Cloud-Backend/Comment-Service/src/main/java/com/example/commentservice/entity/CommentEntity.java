package com.example.commentservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("comment")
public class CommentEntity {
    @TableId(type = IdType.AUTO)
    int id;
    @TableField("article_id")
    int articleId;
    @TableField("user_id")
    int userId;
    @TableField("comment_body")
    String commentBody;
    @TableField("publish_date")
    Date publishDate;
    @TableField("comment_status")
    int commentStatus;
}
