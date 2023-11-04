package com.example.favoservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.favoservice.entity.FavoriteEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteMapper extends BaseMapper<FavoriteEntity> {
}
