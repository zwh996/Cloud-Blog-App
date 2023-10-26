package com.example.commentservice.controller;

import com.example.commentservice.entity.CommentEntity;
import com.example.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/get-by-article-id/{articleId}")
    public ResponseEntity getCommentByArticleId(@PathVariable Integer articleId){
        return new ResponseEntity(commentService.getCommentByArticleId(articleId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Integer commentId){
        int result = commentService.deleteComment(commentId);
        if (result == 1) {
            return new ResponseEntity(HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/add-new-comment")
    public ResponseEntity addNewComment(@RequestBody CommentEntity comment){
        int result = commentService.addNewComment(comment);
        if (result == 1) {
            return new ResponseEntity(HttpStatus.OK);
        } else if (result == -1){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
