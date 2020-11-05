package com.jk.controller;

import com.jk.entity.SysUser;
import com.jk.entity.Tree;
import com.jk.pojo.PageResult;
import com.jk.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

          @Resource
          private UserService userService;





    @RequestMapping("/selectUserInfoByCode")
    SysUser selectUserInfoByCode(@RequestParam String userCode){
        return userService.selectUserInfoByCode(userCode);
    }

    @RequestMapping("/selectTreeList")
    public List<Tree> selectTreeList(@RequestParam Integer userId){
        return userService.selectTreeList(userId);
    }

    @RequestMapping("/selectPowerKeyList")
    public List<String> selectPowerKeyList(@RequestParam Integer userId){
        return userService.selectPowerKeyList(userId);
    }





}
