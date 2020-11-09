package com.jk.service.impl;

import com.jk.dao.CategoryMapper;
import com.jk.dao.GoodsMapper;
import com.jk.entity.*;
import com.jk.pojo.PageResult;
import com.jk.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Override
    public PageResult findCategory(Integer currPage, Integer pageSize, Integer parentId) {
        Long total=categoryMapper.count(parentId);
        List<Category> list=categoryMapper.findCategory(currPage,pageSize,parentId);
        Long totalPage=total%pageSize==0 ? total/pageSize : (total/pageSize+1);

        return new PageResult(total,list,currPage,pageSize,totalPage);
    }

    @Override
    public List<Category> categoryList(Integer parentId) {
        List<Category> categorylist= categoryMapper.categoryList(parentId);
        return categorylist;
    }

    @Override
    public void saveCategory(Category category) {
       if(category.getParentId()==null){
           category.setParentId(0);
       }
       if(category.getId()!=null){
           category.setUpdateTime(new Date());
           categoryMapper.updateByPrimaryKeySelective(category);
       }else{
           category.setCreateTime(new Date());
           categoryMapper.insertSelective(category);
       }
    }

    @Override
    public Category findCategoryOne(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteCategory(Integer id) {
         categoryMapper.deleteByPrimaryKey(id);
         categoryMapper.deleteCategory(id);
    }

    @Override
    public List<Goods> findGoods(Goods goods) {
        return goodsMapper.findGoods(goods);
    }

    @Override
    public List<AttributeKey> attributeKey(Integer categoryId) {
        return goodsMapper.attributeKey(categoryId);
    }

    @Override
    public List<AttributeValue> attributeValue(Integer keyId) {
        return goodsMapper.attributeValue(keyId);
    }

    @Override
    public void saveGoods(Goods goods) {
        GoodsSpecs goodsSpecs = goods.getGoodsSpecs();
        goods.setPrice(goodsSpecs.getPrice());
        goods.setStock(goodsSpecs.getStock());


        System.out.println(goods.getGoodsSpecs());

        goodsSpecs.setProductSpecs("{"+goodsSpecs.getProductSpecs()+"}");
        if(goods.getId()==null){
            goods.setCreateTime(new Date());
            goodsMapper.addGoods(goods);
            goodsSpecs.setCreateTime(new Date());
            goodsMapper.addGoodsSpecs(goodsSpecs);
        }else{
            goods.setUpdateTime(new Date());
            goodsMapper.updateByPrimaryKeySelective(goods);
            /*goodsMapper.saveGoodsSpecs(goodsSpecs,goods.getId());*/
        }


    }

    @Override
    public Goods findGoodsOne(Integer id) {
        return goodsMapper.findGoodsOne(id);
    }


}
