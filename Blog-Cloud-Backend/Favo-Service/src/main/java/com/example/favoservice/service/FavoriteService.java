package com.example.favoservice.service;

import com.example.favoservice.command.ArticleCommand;
import com.example.favoservice.entity.FavoriteEntity;

import java.util.List;

public interface FavoriteService {
    void insert(FavoriteEntity favorite);

    Boolean isFavorited(FavoriteEntity favorite);

    void cancel(FavoriteEntity favorite);

    List<ArticleCommand> favoriteArticle(Integer userId);
}
