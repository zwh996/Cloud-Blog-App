package com.example.commentservice;

import com.example.commentservice.command.CommentCommand;
import com.example.commentservice.entity.CommentEntity;
import com.example.commentservice.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class CommentServiceApplicationTests {

    @Autowired
    CommentService commentService;

    @Test
    void addCommentTest() {
        CommentEntity comment = new CommentEntity();
        comment.setCommentBody("555");
        comment.setCommentStatus(1);
        comment.setPublishDate(new Date());
        comment.setArticleId(3);
        comment.setUserId(6);
        int res = commentService.addNewComment(comment);
        assertEquals(1, res);
    }

    @Test
    void getCommentTest() {
        List<CommentCommand> res = commentService.getCommentByArticleId(3);
        assertEquals("666", res.get(0).getCommentBody());
    }

    @Test
    void removeCommentTest() {
        int res = commentService.deleteComment(6);
        assertEquals(1, res);
    }
}
