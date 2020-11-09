package com.jk.dao;

import com.jk.entity.AttributeKey;
import com.jk.entity.AttributeValue;
import com.jk.entity.Goods;
import com.jk.entity.GoodsSpecs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> findGoods(Goods goods);

    List<AttributeKey> attributeKey(Integer categoryId);

    List<AttributeValue> attributeValue(Integer keyId);

    void addGoods(Goods goods);

    void addGoodsSpecs(GoodsSpecs goodsSpecs);

    Goods findGoodsOne(Integer id);

    void saveGoodsSpecs(@Param("goodsSpecs") GoodsSpecs goodsSpecs,@Param("id") Integer id);
}