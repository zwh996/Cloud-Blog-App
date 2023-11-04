package com.example.favoservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("favorite")
public class FavoriteEntity {
    @TableId
    private Integer id;
    @TableField("user_id")
    private Integer userId;
    @TableField("article_id")
    private Integer articleId;
    @TableField("favorite_date")
    private Date favoriteDate;
    @TableField("is_valid")
    private Integer isValid;
}
