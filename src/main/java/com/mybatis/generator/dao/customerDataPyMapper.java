package com.mybatis.generator.dao;

import com.mybatis.generator.model.customerDataPy;

public interface customerDataPyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(customerDataPy record);

    int insertSelective(customerDataPy record);

    customerDataPy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(customerDataPy record);

    int updateByPrimaryKeyWithBLOBs(customerDataPy record);

    int updateByPrimaryKey(customerDataPy record);
}