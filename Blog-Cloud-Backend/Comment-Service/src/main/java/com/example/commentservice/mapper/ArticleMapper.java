package com.example.commentservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commentservice.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {
}
