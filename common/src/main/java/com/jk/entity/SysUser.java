package com.jk.entity;

import lombok.Data;

@Data
public class SysUser {

    private Integer id;

    private String usercode;

    private String username;

    private String password;

    private String salt;

}
