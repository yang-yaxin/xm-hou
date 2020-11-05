package com.jk.service;

import com.jk.entity.SysUser;
import com.jk.entity.Tree;
import com.jk.pojo.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "cost-provider")
public interface UserService {


    @RequestMapping("/selectUserInfoByCode")
    SysUser selectUserInfoByCode(@RequestParam String userCode);
    @RequestMapping("/selectTreeList")
    List<Tree> selectTreeList(@RequestParam Integer userId);

    @RequestMapping("/selectPowerKeyList")
    List<String> selectPowerKeyList(@RequestParam Integer userId);

}
