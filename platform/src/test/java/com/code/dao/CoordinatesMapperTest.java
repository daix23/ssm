package com.code.dao;

import com.code.BaseTest;
import com.code.entity.Coordinates;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-29
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
public class CoordinatesMapperTest extends BaseTest {

    @Autowired
    private CoordinatesMapper coordinatesMapper;

    @Test
    public void getCoorById(){
        Integer id = 2;
        Coordinates coordinates = coordinatesMapper.selectByPrimaryKey(id);
        System.out.println(coordinates.getGeo());
    }

    @Test
    public  void testInsert(){
        Coordinates one = new Coordinates();
        one.setGeotype("POINT");
        one.setGeo("POINT(120.57752 29.957996)");
        Date date = new Date();
        one.setEntrytime(date);
        one.setModifytime(date);
        one.setRemark("备注");
        int result = coordinatesMapper.insert(one);
        System.out.println("insert:" + result);
    }

    @Test
    public  void testUpdateSelective(){
        Coordinates one = new Coordinates();
        one.setId(9);
        one.setGeo("POINT(120.57744 29.957955)");
        Date date = new Date();
        one.setModifytime(date);
        one.setRemark("备注22");
        int result = coordinatesMapper.updateByPrimaryKeySelective(one);
        System.out.println("update:" + result);
    }

}
