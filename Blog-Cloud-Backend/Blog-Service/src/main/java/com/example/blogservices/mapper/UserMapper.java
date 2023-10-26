package com.example.blogservices.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blogservices.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
