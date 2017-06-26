package com.code.service;

import com.code.entity.Users;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-26
 * Time: 下午4:09
 * To change this template use File | Settings | File Templates.
 */
public interface UsersService {

    /**
     * 根据id查询一个人
     * @param id
     * @return
     */
    public Users getUserById(Integer id);

}
