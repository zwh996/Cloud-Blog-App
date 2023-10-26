package com.example.blogservices.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blogservices.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper extends BaseMapper<TagEntity> {
}
