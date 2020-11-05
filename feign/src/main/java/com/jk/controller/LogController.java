package com.jk.controller;

import com.jk.entity.LogBean;
import com.jk.pojo.PageResult;
import com.jk.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LogController {
    @Resource
    private LogService logService;

    @RequestMapping("toLog")
    public String toLog(){
        return "log/log";
    }
    @RequestMapping("logList")
    @ResponseBody
    public PageResult findPage(@RequestParam(value = "currPage", defaultValue = "1") Integer currPage, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, LogBean log) {
        return logService.findPage(currPage, pageSize,log);
    }
}

