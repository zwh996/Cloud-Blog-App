package com.example.commentservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.commentservice.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {
}
