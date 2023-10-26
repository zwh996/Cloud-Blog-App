package com.example.blogservices.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.blogservices.command.ArticleCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@TableName("article")
@NoArgsConstructor
public class ArticleEntity {

    @TableId(type = IdType.AUTO)
    private int id;

    @TableField("category_id")
    private Integer categoryId;

    @TableField("article_title")
    private String articleTitle;

    private String mdcontent;

    private String htmlcontent;

    @TableField("article_summary")
    private String articleSummary;

    @TableField("user_id")
    private Integer userId;

    @TableField("publish_date")
    private Date publishdate;

    @TableField("edit_time")
    private Date edittime;

    @TableField("article_status")
    private Integer articleStatus;

    @TableField("article_pageviews")
    private Integer articlePageviews;

    @TableField("article_comments")
    private Integer articleComments;

    @TableField("article_favorites")
    private Integer articleFavorites;

    public ArticleEntity(ArticleCommand articleCommand) {
        this.categoryId = articleCommand.getCategoryId();
        this.articleTitle = articleCommand.getArticleTitle();
        this.mdcontent = articleCommand.getMdcontent();
        this.htmlcontent = articleCommand.getHtmlcontent();
        this.userId = articleCommand.getUserId();
        this.articleStatus = articleCommand.getArticleStatus();
        this.articlePageviews = articleCommand.getArticlePageviews();
        this.articleComments = articleCommand.getArticleComments();
        this.articleFavorites = articleCommand.getArticleFavorites();
    }

}
