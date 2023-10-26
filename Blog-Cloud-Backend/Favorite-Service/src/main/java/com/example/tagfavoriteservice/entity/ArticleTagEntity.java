package com.example.tagfavoriteservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("article_tag")
public class ArticleTagEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("article_id")
    private Integer articleId;
    @TableField("tag_id")
    private Integer tagId;
}
