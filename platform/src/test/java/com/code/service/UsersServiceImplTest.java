package com.code.service;

import com.code.BaseTest;
import com.code.entity.Users;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-26
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class UsersServiceImplTest extends BaseTest {

    @Autowired
    private UsersService usersService;

    @Test
    public void testGetUserById(){
        Integer id = 2;
        Users users = usersService.getUserById(id);
        System.out.println(users.getName());
    }

}
