package com.example.favoservice;

import com.example.favoservice.entity.FavoriteEntity;
import com.example.favoservice.service.FavoriteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.Assert.assertTrue;


@SpringBootTest
class FavoServiceApplicationTests {


    @Autowired
    FavoriteService favoriteService;

    @Test
    void insertTest() {
        FavoriteEntity favorite = new FavoriteEntity();
        favorite.setFavoriteDate(new Date());
        favorite.setIsValid(1);
        favorite.setArticleId(3);
        favorite.setUserId(7);
        favoriteService.insert(favorite);
    }

    @Test
    void isFavoritedTest() {
        FavoriteEntity favorite = new FavoriteEntity();
        favorite.setArticleId(3);
        favorite.setUserId(7);
        assertTrue("res wrong", favoriteService.isFavorited(favorite));
    }

    @Test
    void cancleTest(){
        FavoriteEntity favorite = new FavoriteEntity();
        favorite.setArticleId(3);
        favorite.setUserId(7);
        favoriteService.cancel(favorite);
    }

    @Test
    void favoriteArticleTest() {
        assertTrue("res wrong",
                favoriteService.favoriteArticle(5).get(0).getArticleId() == 3);
    }


}
