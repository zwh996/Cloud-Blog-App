package com.example.commentservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("message")
public class Message {
    @TableId(type = IdType.AUTO)
    private Integer messageId;
    @TableField("comment_user_id")
    private Integer commentuserid;
    @TableField("user_id")
    private Integer userId;
    @TableField("article_id")
    private Integer articleId;
    @TableField("comment_id")
    private Integer commentId;
    @TableField("message_type")
    private Integer messageType;
    @TableField("message_isread")
    private Integer messageIsread;
    @TableField("publish_date")
    private Date publishdate;
}
