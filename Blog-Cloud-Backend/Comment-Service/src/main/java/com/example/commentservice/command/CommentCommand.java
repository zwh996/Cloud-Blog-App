package com.example.commentservice.command;

import lombok.Data;

import java.util.Date;

@Data
public class CommentCommand {
    private Integer commentId;

    private Integer articleId;

    private Integer userId;

    private String commentBody;

    private Date publishdate;

    private String userPicture;

    private String userNickname;

}
