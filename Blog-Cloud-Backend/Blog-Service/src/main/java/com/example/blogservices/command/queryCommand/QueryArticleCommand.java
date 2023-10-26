package com.example.blogservices.command.queryCommand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryArticleCommand {
    Integer pageNum;
    Integer pageSize;
    Integer categoryId;
    Integer userId;
    String keyword;
    String userNickname;
}
