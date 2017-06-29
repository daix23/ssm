package com.code.dao;

import com.code.BaseTest;
import com.code.entity.Coordinates;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-29
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
public class CoordinatesMapperTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Test
    public void testSelectAll() {
        //分页，pageNum - 第N页， pageSize - 每页M条数
        Page<Coordinates> page = PageHelper.startPage(2, 5);
        //selectAll查询出的List即为上面定义的page
        List<Coordinates> list = coordinatesMapper.queryAll();
        //注意：
        //使用PageHelper.startPage只是针对接下来的一条查询语句，
        //如果又查询了一次数据，则还需要使用一次PageHelper.startPage
        logger.info("获取所有Coordinates信息，当前页{}条记录", page.size());
        logger.info("获取所有Coordinates信息，一共{}条记录", page.getTotal());
        logger.info("获取所有Coordinates信息，一共{}页", page.getPages());
        logger.info("获取所有Coordinates信息，第{}页", page.getPageNum());
        logger.info("获取所有Coordinates信息，每页{}条记录", page.getPageSize());
        logger.info("获取所有Coordinates信息，获得记录：{}", page);
        //使用PageInfo封装
        PageInfo<Coordinates> info = new PageInfo<Coordinates>(page);
        for (Coordinates one : list) {
            System.out.println(one.getId()+"___"+one.getModifytime());
        }
        logger.info("info.getPages:{}",info);
    }


}
