package com.code.service;

import com.code.entity.Uploadfile;

/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 17-6-28
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public interface UploadfileService {


    /**
     * 上传文件
     * @param uploadfile
     * @return
     */
    public int uploadfile(Uploadfile uploadfile);
}
