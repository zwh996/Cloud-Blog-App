package com.example.tagfavoriteservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tagfavoriteservice.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {
}
