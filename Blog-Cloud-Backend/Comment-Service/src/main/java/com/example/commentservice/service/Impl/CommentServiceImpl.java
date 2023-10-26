package com.example.commentservice.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.commentservice.command.CommentCommand;
import com.example.commentservice.entity.ArticleEntity;
import com.example.commentservice.entity.CommentEntity;
import com.example.commentservice.entity.Message;
import com.example.commentservice.entity.UserEntity;
import com.example.commentservice.mapper.ArticleMapper;
import com.example.commentservice.mapper.CommentMapper;
import com.example.commentservice.mapper.MessageMapper;
import com.example.commentservice.service.CommentService;
import com.example.commentservice.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<CommentCommand> getCommentByArticleId(Integer articleId) {
        log.info("query Id:{}", articleId);
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        return convertToCommand(commentMapper.selectList(queryWrapper));
    }

    @Override
    public int deleteComment(Integer commentId) {
        try {
            commentMapper.deleteById(commentId);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Transactional
    @Override
    public int addNewComment(CommentEntity comment) {
        log.info("comment input:{}", comment);
        try {
            if (comment.getArticleId() > 0) {
                UpdateWrapper<ArticleEntity> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", comment.getArticleId()); // 更新条件，假设使用id作为唯一标识
                String sql = "article_comments = article_comments + 1";
                updateWrapper.setSql(sql);
                //评论数加一
                articleMapper.update(new ArticleEntity(), updateWrapper);
            }
            comment.setPublishDate(new Date());
            commentMapper.insert(comment);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public List<CommentCommand> convertToCommand(List<CommentEntity> entityList) {
        List<CommentCommand> listResult = new ArrayList<>();
        for (CommentEntity tmpEntity : entityList) {
            log.info("tmpEntity:{}",tmpEntity);
            CommentCommand tmpCommand = new CommentCommand();
            BeanUtils.copyProperties(tmpEntity, tmpCommand);
            UserEntity user = userMapper.selectById(tmpEntity.getUserId());
            tmpCommand.setUserNickname(user.getUserNickname());
            tmpCommand.setUserPicture(user.getUserPicture());
            listResult.add(tmpCommand);
        }
        return listResult;
    }
}
