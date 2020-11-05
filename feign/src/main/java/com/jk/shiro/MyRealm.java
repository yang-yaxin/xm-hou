package com.jk.shiro;

import com.jk.entity.SysUser;
import com.jk.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    // 授权  五表联查，查询出当前用户所属的权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 1. 获取当前用户
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();

        // 2. 根据用户id查询 ，当前用户所属的权限字   权限字
        List<String> keyList = userService.selectPowerKeyList(user.getId());

        // 3. 将权限字集合放入到授权器中
        SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
        sai.addStringPermissions(keyList);
       return sai;


    }

    // 认证  判断当前登录的用户和密码是否正确
    // AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1. 获取登录页面用户输入的用户名
        String userCode = authenticationToken.getPrincipal().toString();
        // 2. 调用service层接口，通过用户名查询用户信息
        SysUser user = userService.selectUserInfoByCode(userCode);
        // 3. 如果用户对象为null，用户名不存在, 则返回账号不存在异常
        if(user == null){
            // 不知道账号异常 （账号不存在）
            throw new UnknownAccountException();
        }
        // 获取盐值
        ByteSource saltByte = ByteSource.Util.bytes(user.getSalt());
        // 4. 用户对象有数据
        // 5. 验证密码 参数传递(用户对象, 用户密码, 当前realm对象名称)
        SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(user, user.getPassword(), saltByte, this.getName());
        return sai;
    }



    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";//加密方式 MD5  SHA
        Object crdentials = "123";//密码原值
        Object salt = "zf";//盐值 (用户账号、UUID、时间戳、随机字母 作为盐值，盐值必须保持唯一)
        ByteSource saltByte = ByteSource.Util.bytes(salt);
        int hashIterations = 1024;//加密1024次  散列次数
        Object result = new SimpleHash(hashAlgorithmName,crdentials,saltByte,hashIterations);
        //result 加密后的值设置为数据库中的密码
        System.out.println(result);
    }


}
