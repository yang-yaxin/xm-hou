package com.jk.controller;

import com.jk.pojo.PageResult;
import com.jk.service.UserService;


import com.jk.entity.SysUser;
import com.jk.entity.Tree;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/user/login")
    public String login(SysUser user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken upt = new UsernamePasswordToken(user.getUsercode(), user.getPassword());
        try {
            // 不抛异常代表登录成功
            subject.login(upt);
            return "登录成功";
        } catch (UnknownAccountException uae) {
            return "账号不存在";
        } catch (IncorrectCredentialsException ice) {
            return "密码错误";
        } catch (AuthenticationException ae) {
            return "登录失败，网络错误";
        }
    }

    @RequestMapping("user/queryTree")
    public List<Tree> queryTree() {
        System.out.println("登录成功");
        //查询当前用户所属的权限树
        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        return userService.selectTreeList(user.getId());
    }

}
