package com.example.favoservice.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.favoservice.command.ArticleCommand;
import com.example.favoservice.entity.ArticleEntity;
import com.example.favoservice.entity.ArticleTagEntity;
import com.example.favoservice.entity.FavoriteEntity;
import com.example.favoservice.mapper.*;
import com.example.favoservice.service.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteMapper favoriteMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    ArticleTagMapper articleTagMapper;


    @Override
    public void insert(FavoriteEntity favorite) {
        favorite.setFavoriteDate(new Date());
        favorite.setIsValid(1);
        favoriteMapper.insert(favorite);
    }

    @Override
    public Boolean isFavorited(FavoriteEntity favorite) {
        QueryWrapper<FavoriteEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", favorite.getUserId()).eq("article_id", favorite.getArticleId());
        if (Objects.isNull(favoriteMapper.selectOne(queryWrapper))) {
            return false;
        }
        return true;
    }

    @Override
    public void cancel(FavoriteEntity favorite) {
        QueryWrapper<FavoriteEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", favorite.getUserId()).eq("article_id", favorite.getArticleId());
        favoriteMapper.delete(queryWrapper);
    }

    @Override
    public List<ArticleCommand> favoriteArticle(Integer userId) {
        List<ArticleCommand> articleCommands = new ArrayList<>();
        QueryWrapper<FavoriteEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        for(FavoriteEntity tmpEntity: favoriteMapper.selectList(queryWrapper)){
            ArticleEntity article = articleMapper.selectById(tmpEntity.getArticleId());
            if(article.getArticleStatus() == 1){
                ArticleCommand articleCommand = new ArticleCommand();
                //注入已有属性
                BeanUtils.copyProperties(article,articleCommand);
                articleCommand.setArticleId(article.getId());
                log.info(articleCommand.toString());
                //获取用户昵称
                articleCommand.setUserNickname(userMapper.selectById(article.getUserId()).getUserNickname());
                //获取文章标签
                List<String> tags = new ArrayList<>();
                QueryWrapper<ArticleTagEntity> articleTagqueryWrapper = new QueryWrapper<>();
                articleTagqueryWrapper.eq("article_id", article.getId());
                List<ArticleTagEntity> articleTags = articleTagMapper.selectList(articleTagqueryWrapper);
                for (ArticleTagEntity articleTag : articleTags) {
                    tags.add(tagMapper.selectById(articleTag.getTagId()).getTagName());
                }
                articleCommand.setTags(tags);
                //加入集合
                articleCommands.add(articleCommand);
            }
        }
        return articleCommands;
    }
}
