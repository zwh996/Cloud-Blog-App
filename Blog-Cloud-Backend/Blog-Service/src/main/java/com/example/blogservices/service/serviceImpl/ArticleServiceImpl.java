package com.example.blogservices.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blogservices.command.ArticleCommand;
import com.example.blogservices.entity.ArticleEntity;
import com.example.blogservices.entity.ArticleTagEntity;
import com.example.blogservices.entity.TagEntity;
import com.example.blogservices.entity.UserEntity;
import com.example.blogservices.mapper.ArticleMapper;
import com.example.blogservices.mapper.ArticleTagMapper;
import com.example.blogservices.mapper.TagMapper;
import com.example.blogservices.mapper.UserMapper;
import com.example.blogservices.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ArticleTagMapper articleTagMapper;

    @Value("${prefix}")
    String prefix;

    @Override
    public ResponseEntity getArticleById(int id) {
        ArticleEntity entity = articleMapper.selectById(id);
        if (Objects.isNull(entity)) {
            throw new NullPointerException("entity not found.");
        }
        ArticleCommand articleCommand = articleToCommand(entity);
        return new ResponseEntity(articleCommand, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getArticleByUserId(int userId, int pageNum) {
        log.info("name:{}", userId);
        Page<ArticleEntity> page = new Page<>(pageNum, 6);
        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_id", userId).eq("article_status", 1);
        IPage<ArticleEntity> ipage = articleMapper.selectPage(page, queryWrapper);
        List<ArticleEntity> entityList = ipage.getRecords();
        List<ArticleCommand> listResult = new ArrayList<>();
        for (ArticleEntity tmp : entityList) {
            listResult.add(articleToCommand(tmp));
        }
        return new ResponseEntity(listResult, HttpStatus.OK);
    }

    @Transactional
    public ArticleCommand articleToCommand(ArticleEntity article) {
        ArticleCommand articleCommand = new ArticleCommand();
        //inject attributes
        BeanUtils.copyProperties(article, articleCommand);
        articleCommand.setArticleId(article.getId());
        //get user profile
        UserEntity user = userMapper.selectById(article.getUserId());
        articleCommand.setUserNickname(user.getUserNickname());
        articleCommand.setUserPicture(prefix + user.getUserPicture());
        //get article tag
        List<String> tagNames = new ArrayList<>();
        QueryWrapper<ArticleTagEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", article.getId());
        List<ArticleTagEntity> tags = articleTagMapper.selectList(queryWrapper);
        for (ArticleTagEntity tag : tags) {
            tagNames.add(tagMapper.selectById(tag.getTagId()).getTagName());
        }
        articleCommand.setTags(tagNames);
        return articleCommand;
    }

    @Override
    public ResponseEntity getArticleByName(String name, int pageNum) {
        log.info("name:{}", name);
        Page<ArticleEntity> page = new Page<>(pageNum, 6);
        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        if (!name.equals(" ")) {
            queryWrapper.like("article_title", name).eq("article_status", 1);
        }
        IPage<ArticleEntity> ipage = articleMapper.selectPage(page, queryWrapper);
        List<ArticleEntity> entityList = ipage.getRecords();
        List<ArticleCommand> listResult = new ArrayList<>();
        for (ArticleEntity tmp : entityList) {
            listResult.add(articleToCommand(tmp));
        }
        return new ResponseEntity(listResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getArticleBySum(String sum, int pageNum) {
        Page<ArticleEntity> page = new Page<>(pageNum, 6);
        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("article_summary", sum).eq("article_status", 1);
        IPage<ArticleEntity> ipage = articleMapper.selectPage(page, queryWrapper);
        List<ArticleEntity> entityList = ipage.getRecords();
        List<ArticleCommand> listResult = new ArrayList<>();
        for (ArticleEntity tmp : entityList) {
            listResult.add(articleToCommand(tmp));
        }
        return new ResponseEntity(listResult, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity createArticle(ArticleCommand articleCommand) {
        Date date = new Date();
        ArticleEntity article = new ArticleEntity(articleCommand);
        article.setPublishdate(date);
        article.setEdittime(date);
        //split article
        String stripHtml = stripHtml(article.getHtmlcontent());
        article.setArticleSummary(stripHtml.substring(0, stripHtml.length() > 50 ? 50 : stripHtml.length()));

        int ret = articleMapper.insert(article);
        //bulid relation
        for (String tagName : articleCommand.getTags()) {
            ArticleTagEntity articleTag = new ArticleTagEntity();
            articleTag.setArticleId(article.getId());
            QueryWrapper<TagEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("tag_Name", tagName);
            TagEntity tag = tagMapper.selectOne(wrapper);
            if (Objects.isNull(tag)) {
                tag = new TagEntity();
                tag.setTagName(tagName);
                tagMapper.insert(tag);
                tag = tagMapper.selectOne(wrapper);
            }
            articleTag.setTagId(tag.getId());
            articleTagMapper.insert(articleTag);
        }
        ResponseEntity<Integer> responseEntity = new ResponseEntity(ret, HttpStatus.OK);
        return responseEntity;
    }

    public String stripHtml(String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

    @Override
    public ResponseEntity removeArticle(int id) {
        ArticleEntity article = articleMapper.selectById(id);
        article.setArticleStatus(0);
        articleMapper.updateById(article);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity updateArticle(ArticleCommand articleCommand) {
        ArticleEntity article = articleMapper.selectById(articleCommand.getArticleId());
        BeanUtils.copyProperties(articleCommand, article);
        article.setArticleStatus(0);
        articleMapper.updateById(article);
        //delete origin tags
        QueryWrapper<ArticleTagEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleCommand.getArticleId());
        articleTagMapper.delete(wrapper);
        //add tmp tags
        if (articleCommand.getTags() != null && articleCommand.getTags().size() != 0) {
            for (String tagName : articleCommand.getTags()) {
                ArticleTagEntity articleTag = new ArticleTagEntity();
                articleTag.setArticleId(article.getId());
                QueryWrapper<TagEntity> queryWrapper = new QueryWrapper<>();
                wrapper.eq("tag_Name", tagName);
                TagEntity tag = tagMapper.selectOne(queryWrapper);
                if (Objects.isNull(tag)) {
                    tag = new TagEntity();
                    tag.setTagName(tagName);
                    tagMapper.insert(tag);
                    tag = tagMapper.selectOne(queryWrapper);
                }
                articleTag.setTagId(tag.getId());
                articleTagMapper.insert(articleTag);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
