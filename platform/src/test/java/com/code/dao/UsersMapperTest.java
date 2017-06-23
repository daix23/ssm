package com.code.dao;

import com.code.BaseTest;
import com.code.entity.Users;
import com.code.util.DateTimeUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-22
 * Time: 上午11:22
 * To change this template use File | Settings | File Templates.
 */
public class UsersMapperTest extends  BaseTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testQueryById() throws Exception {
        Integer id = 2;
        Users users = usersMapper.selectByPrimaryKey(id);
        System.out.println(users.getName());
    }

    @Test
    public  void testInsert(){
        Users users = new Users();
        users.setName("fucking");
        users.setAge(27);
        users.setWeight(70.0);
        String dateStr = "1990-08-08";
        Date date = DateTimeUtil.strToDate2(dateStr);
        users.setBirthday(date);
        int result = usersMapper.insertSelective(users);
        System.out.println("insert:" + result);
    }

}
