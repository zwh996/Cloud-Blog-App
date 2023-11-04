package com.example.favoservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.favoservice.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {
}
