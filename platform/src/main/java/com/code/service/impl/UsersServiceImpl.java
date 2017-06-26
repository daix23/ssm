package com.code.service.impl;

import com.code.dao.UsersMapper;
import com.code.entity.Users;
import com.code.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-26
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UsersServiceImpl implements UsersService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired// 注入Service依赖
    private UsersMapper usersMapper;

    @Override
    public Users getUserById(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }
}
