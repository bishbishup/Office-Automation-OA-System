package com.imooc.oa.service;

import com.imooc.oa.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService = new UserService();

    @Test
    public void checkLogin() {
        User user = userService.checkLogin("m8","test");
        System.out.println(user);
    }
}