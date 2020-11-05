package com.jk.service;

import com.jk.entity.SysUser;
import com.jk.entity.Tree;
import com.jk.mapper.UserMapper;
import com.jk.pojo.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServicelmpl implements UserService{

    @Resource
    private UserMapper userMapper;



    @Override
    public SysUser selectUserInfoByCode(String userCode) {
        return userMapper.selectUserInfoByCode(userCode);
    }

    @Override
    public List<Tree> selectTreeList(Integer userId) {
        Integer pid = 1;
        List<Tree> treeList = nodesList(pid, userId);
        return treeList;
    }

    @Override
    public List<String> selectPowerKeyList(Integer userId) {
        return userMapper.selectPowerKeyList(userId);
    }

    private List<Tree> nodesList(Integer pid, Integer userId){
        List<Tree> treeList = userMapper.selectTreeList(pid, userId);
        for (Tree tree :
                treeList) {
            List<Tree> nodeList = nodesList(tree.getId(), userId);
            // 没有子节点
            if(nodeList == null || nodeList.size() <= 0) {
                tree.setSelectable(true);
                tree.setLeaf(true);
            } else {
                tree.setSelectable(false);
                tree.setNodes(nodeList);
            }
        }
        return treeList;
    }




}
