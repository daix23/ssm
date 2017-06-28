package com.code.dao;

import com.code.BaseTest;
import com.code.entity.Uploadfile;
import com.code.util.DateTimeUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-28
 * Time: 上午10:19
 * To change this template use File | Settings | File Templates.
 */
public class UploadfileMapperTest extends BaseTest {

    @Autowired
    private UploadfileMapper uploadfileMapper;

    @Test
    public void testQueryAll() throws Exception {
        List<Uploadfile> uploadfileList = uploadfileMapper.queryAll(0,5);
        for (Uploadfile one : uploadfileList) {
            System.out.println(one.getFilename()+"_________"+ DateTimeUtil.DateToStr4(one.getUploadtime()));
        }
    }

}
