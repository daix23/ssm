package com.code.web;

import com.code.dto.Result;
import com.code.entity.Users;
import com.code.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-26
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/users") // url:/模块/资源/{id}/细分 /seckill/list
public class UsersController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersService usersService;


    /**
     * http://localhost:8080/platform/users/3/user
     * ajax json
     */
    @RequestMapping(value = "/{userId}/user", method = RequestMethod.GET, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    private Result<Users> user(@PathVariable("userId") Integer userId) {
        if (userId == null || userId.equals("")) {
            return new Result<Users>(false, "学号不能为空");
        }
        Users user = usersService.getUserById(userId);
        return new Result<Users>(true, user);
    }



}
