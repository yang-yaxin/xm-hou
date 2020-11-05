package com.jk.service;

import com.jk.entity.LogBean;
import com.jk.pojo.PageResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogService {
    @Resource
    private MongoTemplate mongoTemplate;
    public PageResult findPage(Integer currPage, Integer pageSize, LogBean log) {

        Query query=new Query();
        if(log.getMethodName()!=null && log.getMethodName()!=""){
            query.addCriteria(Criteria.where("methodName").regex(log.getMethodName(),"i"));
        }
        long total = mongoTemplate.count(query, LogBean.class);
        int start=(currPage-1)*pageSize;
        query.skip(start).limit(pageSize);
        List<LogBean> list = mongoTemplate.find(query, LogBean.class);
        Long totalPage = total%pageSize == 0 ? total/pageSize : (total/pageSize + 1);
        return new PageResult(total, list, currPage, pageSize, totalPage);
    }
}
