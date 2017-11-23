package com.yazuo.intelligent.logger.aop;

import com.yazuo.intelligent.logger.ErrorLogger;
import com.yazuo.intelligent.logger.InfoLogger;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Resource
    ErrorLogger errorLogger;
    @Resource
    InfoLogger infoLogger;
    @Around("@annotation(apiOperation)")
    public Object log(ProceedingJoinPoint point, ApiOperation apiOperation) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            Object result = point.proceed();
            infoLogger.log(start,(MethodSignature) point.getSignature(),point.getArgs(),result,null);
           return result;
        } catch (Throwable throwable) {
            errorLogger.log(start,(MethodSignature) point.getSignature(),point.getArgs(),null,throwable);
            throw throwable;
        }

    }
}
