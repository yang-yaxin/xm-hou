package com.jk.mapper;


import com.jk.entity.Fei;
import com.jk.entity.Ordering;
import com.jk.entity.SysUser;
import com.jk.entity.Tree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    //费用
    Long findTotal(@Param("seo") Fei seo);

    List<Fei> findPage(@Param("currPage")Integer currPage, @Param("pageSize")Integer pageSize, @Param("seo")Fei seo);

    void add(Fei seo);

    Fei huixian(Integer id);

    void update(Fei seo);

    void del(int parseInt);

    //订餐
    Long findTotalx(@Param("ordering")Ordering ordering);

    List<Ordering> findPagex(@Param("currPage")Integer currPage, @Param("pageSize")Integer pageSize, @Param("ordering")Ordering ordering);

    void xin(Ordering ordering);

    void shan(int parseInt);

    Ordering hui(Integer id);

    void up(Ordering ordering);
    SysUser selectUserInfoByCode(@Param("userCode") String userCode);

    List<Tree> selectTreeList(Integer pid, Integer userId);

    List<String> selectPowerKeyList(Integer userId);
}
