package com.example.commentservice.service;

import com.example.commentservice.command.CommentCommand;
import com.example.commentservice.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    List<CommentCommand> getCommentByArticleId(Integer articleId);

    int deleteComment(Integer commentId);

    int addNewComment(CommentEntity comment);

}
