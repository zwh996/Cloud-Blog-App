package com.example.blogservices.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blogservices.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {
}
