package com.zero.scvzerng.logger.aop;

import com.zero.scvzerng.logger.ErrorLogger;
import com.zero.scvzerng.logger.InfoLogger;
import com.zero.scvzerng.logger.LogMethodSignature;

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
