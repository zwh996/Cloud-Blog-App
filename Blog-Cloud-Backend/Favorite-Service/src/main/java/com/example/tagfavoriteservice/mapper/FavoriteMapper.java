package com.example.tagfavoriteservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tagfavoriteservice.entity.FavoriteEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteMapper extends BaseMapper<FavoriteEntity> {
}
