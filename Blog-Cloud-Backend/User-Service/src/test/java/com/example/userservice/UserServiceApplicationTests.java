package com.example.userservice;

import com.example.userservice.command.UserCommand;
import com.example.userservice.command.queryCommand.UserQueryCommand;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void getUserByUsernameTest() {
        UserCommand command = userService.getUserByUsername("ZWH");
        assertEquals((Integer) 1, command.getUserId());
    }

    @Test
    void getUserByIdTest() {
        UserCommand command = userService.getUserById(1);
        assertEquals("ZWH", command.getUsername());
    }

    @Test
    void nameCheckTest() {
        assertTrue("Name check fail", userService.nameCheck("ZWH") != 1);
    }

    @Test
    void updateTest() {
        UserCommand userCommand = new UserCommand();
        userCommand.setUserId(1);
        userCommand.setUserNickname("zwh955");
        assertEquals(1, userService.update(userCommand));

    }

    @Test
    void getListTest() {
        UserQueryCommand queryCommand = new UserQueryCommand();
        queryCommand.setUserNickname("zwh996");
        queryCommand.setUserName("ZWH");
        queryCommand.setPageNum(1);
        queryCommand.setPageSize(6);
        assertEquals((Integer) 1, userService.getList(queryCommand).get(0).getUserId());
    }


}
