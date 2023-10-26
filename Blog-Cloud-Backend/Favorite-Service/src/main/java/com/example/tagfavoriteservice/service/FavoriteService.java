package com.example.tagfavoriteservice.service;

import com.example.tagfavoriteservice.command.ArticleCommand;
import com.example.tagfavoriteservice.entity.FavoriteEntity;

import java.util.List;

public interface FavoriteService {
    void insert(FavoriteEntity favorite);

    Boolean isFavorited(FavoriteEntity favorite);

    void cancel(FavoriteEntity favorite);

    List<ArticleCommand> favoriteArticle(Integer userId);
}
