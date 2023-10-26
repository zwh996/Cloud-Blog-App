package com.example.tagfavoriteservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tagfavoriteservice.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}