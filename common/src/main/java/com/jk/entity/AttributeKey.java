package com.jk.entity;

import lombok.Data;

import java.util.Date;
@Data
public class AttributeKey {
    private Integer id;

    private Integer categoryId;

    private String attributeName;

    private Date createTime;

    private Date updateTime;


}