package com.jk.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class GoodsSpecs {
    private Integer id;

    private Integer goodspuId;

    private String productSpecs;

    private Integer stock;

    private Integer price;

    private Date createTime;


}