package com.jk.dao;

import com.jk.entity.GoodsSpecs;

public interface GoodsSpecsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpecs record);

    int insertSelective(GoodsSpecs record);

    GoodsSpecs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsSpecs record);

    int updateByPrimaryKey(GoodsSpecs record);
}