package com.example.blogservices;

import com.example.blogservices.command.ArticleCommand;
import com.example.blogservices.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@SpringBootTest
class ArticleServicesApplicationTests {


    @Autowired
    ArticleService articleService;

    @Test
    void getArticleByIdTest() {
        ResponseEntity<ArticleCommand> res = articleService.getArticleById(3);
        assertEquals("Java starter", res.getBody().getArticleTitle());
    }

    @Test
    void getArticleByUesrIdTest() {
        ResponseEntity<List<ArticleCommand>> res = articleService.getArticleByUserId(1, 1);
        assertEquals(6, res.getBody().size());
        assertEquals("Java starter", res.getBody().get(0).getArticleTitle());
    }

    @Test
    void getArticleByNameOrSumTest() {
        ResponseEntity<List<ArticleCommand>> res = articleService.getArticleByName("html", 1);
        assertEquals(4, res.getBody().size());
        assertEquals("first html", res.getBody().get(0).getMdcontent());
        res = articleService.getArticleBySum("first html", 1);
        assertEquals(3, res.getBody().size());
        assertEquals("first html", res.getBody().get(0).getMdcontent());
    }

    @Test
    void createArticleTest() {
        ArticleCommand command = new ArticleCommand();
        command.setArticleId(0);
        command.setArticleTitle("css starter");
        command.setMdcontent("css MDcontent");
        command.setHtmlcontent("css course starter18");
        command.setArticleSummary("css articleSummary test");
        command.setUserId(1);
        command.setArticleStatus(1);
        command.setArticlePageviews(1);
        List<String> list = new ArrayList<>();
        list.add("css");
        command.setTags(list);
        ResponseEntity<Integer> res = articleService.createArticle(command);
        assertTrue("return null", res.getBody() != null);
    }

    @Test
    void updateArticleTest() {
        ArticleCommand command = new ArticleCommand();
        command.setArticleId(30);
        command.setHtmlcontent("css course starter20");
        ResponseEntity res = articleService.updateArticle(command);
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    void deleteArticleTest(){
        ResponseEntity res = articleService.removeArticle(30);
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }
}
