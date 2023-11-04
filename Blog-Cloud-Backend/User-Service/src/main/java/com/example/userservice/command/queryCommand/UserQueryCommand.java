package com.example.userservice.command.queryCommand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryCommand {
    Integer userId;

    Integer pageNum;

    Integer pageSize;

    String userName;

    String userNickname;

    Integer userStatus;
}
