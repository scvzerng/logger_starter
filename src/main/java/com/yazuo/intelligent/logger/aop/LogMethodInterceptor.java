package com.yazuo.intelligent.logger.aop;

import com.yazuo.intelligent.logger.ErrorLogger;
import com.yazuo.intelligent.logger.InfoLogger;
import com.yazuo.intelligent.logger.LogMethodSignature;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


import javax.annotation.Resource;


/**
 * description
 * <p>
 * 2017-12-29 15:41
 *
 * @author scvzerng
 **/
public class LogMethodInterceptor implements MethodInterceptor {
    @Resource
    ErrorLogger errorLogger;
    @Resource
    InfoLogger infoLogger;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            Object result = methodInvocation.proceed();
            infoLogger.log(start,new LogMethodSignature(methodInvocation.getMethod()),methodInvocation.getArguments(),result,null);
            return result;
        } catch (Throwable throwable) {
            errorLogger.log(start,new LogMethodSignature(methodInvocation.getMethod()),methodInvocation.getArguments(),null,throwable);
            throw throwable;
        }
    }



}
