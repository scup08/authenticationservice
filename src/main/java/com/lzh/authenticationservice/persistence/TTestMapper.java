package com.lzh.authenticationservice.persistence;

import com.lzh.authenticationservice.model.entity.TTest;

//@MyBatisRepository
//public interface TTestMapper extends CrudMapper {
public interface TTestMapper  {
    int deleteByPrimaryKey(Long id);

    int insert(TTest record);

    int insertSelective(TTest record);

    TTest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTest record);

    int updateByPrimaryKey(TTest record);
}