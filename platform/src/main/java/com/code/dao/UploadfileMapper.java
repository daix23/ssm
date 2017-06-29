package com.code.dao;

import com.code.entity.Uploadfile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UploadfileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table uploadfile
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table uploadfile
     *
     * @mbg.generated
     */
    int insert(Uploadfile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table uploadfile
     *
     * @mbg.generated
     */
    int insertSelective(Uploadfile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table uploadfile
     *
     * @mbg.generated
     */
    Uploadfile selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table uploadfile
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Uploadfile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table uploadfile
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Uploadfile record);

    /**
     * 查询附件
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return
     */
    List<Uploadfile> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}