package com.jk.aop;

import com.alibaba.fastjson.JSONObject;
import com.jk.entity.LogBean;
import com.jk.service.LogSenderService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private LogSenderService logSenderService;

    // 第一个*代表 所有返回值
    // 第二个*代表所有类
    // 第三个*代表所有方法
    // .. 代表所有参数
    @Pointcut(value = "execution(* com.jk.controller.*.*(..))")
    public void logCut(){}

    @AfterReturning(pointcut = "logCut()", returning = "obj")
    public void saveLog(JoinPoint joinPoint, Object obj) {

        String className = joinPoint.getTarget().getClass().getSimpleName();

        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        // |第1个参数：Name:张三|第二个参数：age:12
        StringBuffer sb = new StringBuffer();
        for (int i=0; i < args.length; i++) {
            sb.append("|第"+(i+1)+"个参数：" + args[i]);
        }

        String responseParam = obj == null ? "" : obj.toString();

        LogBean log = new LogBean();
        log.setClassName(className);
        log.setMethodName(methodName);
        log.setRequestParam(sb.toString());
        log.setResponseParam(responseParam);

        String logJson = JSONObject.toJSONString(log);

        logger.info(logJson);

        logSenderService.send(logJson);

    }
}
