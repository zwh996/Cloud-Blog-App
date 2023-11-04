package com.example.favoservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.favoservice.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
