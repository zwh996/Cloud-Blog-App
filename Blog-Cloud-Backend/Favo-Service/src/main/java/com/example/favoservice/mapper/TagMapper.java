package com.example.favoservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.favoservice.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper extends BaseMapper<TagEntity> {
}
