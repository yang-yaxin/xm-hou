package com.jk.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {


    @RequestMapping("/china")
    public String china(){
        return "show";
    }

    @RequestMapping("page/toLogin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }


}
