package com.example.blogservices.service;

import com.example.blogservices.command.ArticleCommand;
import com.example.blogservices.command.queryCommand.QueryArticleCommand;
import com.example.blogservices.entity.ArticleEntity;
import org.springframework.http.ResponseEntity;

public interface ArticleService {
    public ResponseEntity getArticleById(int id);
    public ResponseEntity getArticleByName(String name,int pageNum);
    public ResponseEntity getArticleBySum(String sum, int pageNum);
    public ResponseEntity createArticle(ArticleCommand articleCommand);
    public ResponseEntity removeArticle(int id);
    public ResponseEntity updateArticle(ArticleCommand articleCommand);
}
