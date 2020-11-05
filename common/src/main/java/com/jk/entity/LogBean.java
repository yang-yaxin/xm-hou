package com.jk.entity;

import lombok.Data;

@Data
public class LogBean {
    private String id;
    private String className;
    private String methodName;
    private String requestParam;
    private String responseParam;
}
