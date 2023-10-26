package com.example.blogservices.controller;

import com.example.blogservices.command.ArticleCommand;
import com.example.blogservices.command.queryCommand.QueryArticleCommand;
import com.example.blogservices.entity.ArticleEntity;
import com.example.blogservices.service.ArticleService;
import com.example.blogservices.service.CallAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(("/api/blog"))
@Slf4j
public class BlogController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CallAuthService callAuthService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/get-article-by-id/{id}")
    public ResponseEntity getBlogById(@PathVariable int id) {
        log.info("queryId = {}", id);
        return articleService.getArticleById(id);
    }

    @GetMapping("/get-article-by-title/{title}/{pageNum}")
    public ResponseEntity getBlogByTitle(@PathVariable String title, @PathVariable int pageNum) {
        return articleService.getArticleByName(title, pageNum);
    }

    @GetMapping("/get-article-by-sum/{sum}/{pageNum}")
    public ResponseEntity getBlogBySum(@PathVariable String sum, @PathVariable int pageNum) {
        return articleService.getArticleBySum(sum, pageNum);
    }

    @PostMapping("/create-article")
    public ResponseEntity createBlog(@RequestBody ArticleCommand articleCommand) {
        return articleService.createArticle(articleCommand);
    }

    @DeleteMapping("/remove-article/{id}")
    public ResponseEntity removeBlog(@PathVariable Integer id) {
        return articleService.removeArticle(id);
    }

    /**
     * description: All update through this method, including tags change.
     * @param: userId
     * @return:
     */
    @PutMapping("/update-article")
    public ResponseEntity updateBlog(@RequestBody ArticleCommand articleCommand) {
        return articleService.updateArticle(articleCommand);
    }
}
