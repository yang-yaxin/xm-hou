package com.jk.entity;

import lombok.Data;

import java.util.Date;
@Data
public class AttributeValue {
    private Integer id;

    private Integer keyId;

    private String attributeValue;

    private Date createTime;

    private Date updateTime;


}