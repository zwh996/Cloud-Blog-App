package com.example.tagfavoriteservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tagfavoriteservice.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper extends BaseMapper<TagEntity> {
}
