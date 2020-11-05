package com.jk.entity;

import lombok.Data;

import java.util.Date;

@Data
public class InvokeLogModel {
    private Integer uid;
    private String invokeUser;
    private String interfaceName;
    private String interfaceMethod;
    private Date invokeStartTime;
    private Date invokeEndTime;
    private String requestParam;
    private Boolean responseResult;
    private String responseBody;
}
