package com.jk.service;

import com.jk.entity.AttributeKey;
import com.jk.entity.AttributeValue;
import com.jk.entity.Category;
import com.jk.entity.Goods;
import com.jk.pojo.PageResult;

import java.util.List;

public interface GoodsService {
    PageResult findCategory(Integer currPage, Integer pageSize, Integer parentId);

    List<Category> categoryList(Integer parentId);

    void saveCategory(Category category);

    Category findCategoryOne(Integer id);

    void deleteCategory(Integer id);

    List<Goods> findGoods(Goods goods);

    List<AttributeKey> attributeKey(Integer categoryId);

    List<AttributeValue> attributeValue(Integer keyId);

    void saveGoods(Goods goods);

    Goods findGoodsOne(Integer id);
}
