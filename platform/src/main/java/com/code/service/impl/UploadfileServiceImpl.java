package com.code.service.impl;

import com.code.dao.UploadfileMapper;
import com.code.entity.Uploadfile;
import com.code.service.UploadfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-28
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UploadfileServiceImpl implements UploadfileService {

    @Autowired
    private UploadfileMapper uploadfileMapper;

    @Override
    public int uploadfile(Uploadfile uploadfile) {
        return uploadfileMapper.insertSelective(uploadfile);
    }
}
