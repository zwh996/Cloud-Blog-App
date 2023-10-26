package com.example.blogservices.command;

import com.example.blogservices.entity.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleCommand {
    private Integer articleId;

    private Integer categoryId;

    private String articleTitle;

    private String mdcontent;

    private String htmlcontent;

    private String articleSummary;

    private Integer userId;

    private String userPicture;

    private Date publishdate;

    private Date edittime;

    private Integer articleStatus;

    private Integer articlePageviews;

    private Integer articleComments;

    private String userNickname;

    private List<String> tags;

    private Integer articleFavorites;
}
