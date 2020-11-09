package com.jk.controller;

import com.jk.entity.AttributeKey;
import com.jk.entity.AttributeValue;
import com.jk.entity.Category;
import com.jk.entity.Goods;
import com.jk.pojo.PageResult;
import com.jk.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    @RequestMapping("findCategory")
    public PageResult findCategory(@RequestParam(value = "currPage",defaultValue = "1")Integer currPage, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize, @RequestParam(value = "parentId",defaultValue = "0") Integer parentId){
        return goodsService.findCategory(currPage,pageSize,parentId);
    }
    @RequestMapping("categoryList")
    public List<Category> categoryTree( @RequestParam(value = "parentId",defaultValue = "0") Integer parentId){
        return goodsService.categoryList(parentId);
    }

    @RequestMapping("saveCategory")
    public void saveCategory(@RequestBody Category category){
         goodsService.saveCategory(category);

    }
    @RequestMapping("findCategoryOne")
    public Category findCategoryOne(@RequestParam Integer id){
        return goodsService.findCategoryOne(id);
    }
    @RequestMapping("deleteCategory")
    public void deleteCategory(@RequestParam Integer id){
        goodsService.deleteCategory(id);
    }

    @RequestMapping("findGoods")
    public List<Goods> findGoods(@RequestBody Goods goods){
        return goodsService.findGoods(goods);
    }
    @RequestMapping("attributeKey")
    public List<AttributeKey> attributeKey(@RequestParam Integer categoryId){
        return goodsService.attributeKey(categoryId);
    }
    @RequestMapping("attributeValue")
    public List<AttributeValue> attributeValue(@RequestParam Integer keyId){
        return goodsService.attributeValue(keyId);
    }
    @RequestMapping("saveGoods")
    public void saveGoods(@RequestBody Goods goods){
        goodsService.saveGoods(goods);
    }
    @RequestMapping("findGoodsOne")
    public Goods findGoodsOne(@RequestParam Integer id){
        return goodsService.findGoodsOne(id);
    }
}
