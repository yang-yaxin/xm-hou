package com.jk.dao;

import com.jk.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    Long count(@Param("parentId") Integer parentId);

    List<Category> findCategory(@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize,@Param("parentId") Integer parentId);

    List<Category> categoryList(Integer parentId);

    void deleteCategory(Integer id);
}