package com.yazuo.intelligent.logger.cache;

import com.yazuo.intelligent.logger.ErrorLogger;
import com.yazuo.intelligent.logger.InfoLogger;
import io.swagger.annotations.ApiOperation;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.interceptor.CacheInterceptor;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;

/**
 * 缓存命中日志拦截器
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Year: 2017-2017/11/18-18:17
 * Project:xycrm_intelligent_service
 * Package:com.yazuo.intelligent.cache
 * To change this template use File | Settings | File Templates.
 */

public class CacheLogInterceptor extends CacheInterceptor {
    @Resource
    private InfoLogger infoLogger;
    @Resource
    private ErrorLogger errorLogger;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Signature signature =new LogMethodSignature(invocation);
        ApiOperation apiOperation = invocation.getMethod().getAnnotation(ApiOperation.class);

        long start = System.currentTimeMillis();
        try{
            Object result = super.invoke(invocation);
            infoLogger.log(start,signature,apiOperation,invocation.getArguments(),result,null);
            return result;
        }catch (Exception e){
            errorLogger.log(start,signature,apiOperation,invocation.getArguments(),null,e);
            throw e;
        }

    }

    public static class LogMethodSignature implements MethodSignature {
        private Method method;
        public LogMethodSignature(MethodInvocation invocation) {
            this.method = invocation.getMethod();
        }

        @Override
        public Class getReturnType() {
            return method.getReturnType();
        }

        @Override
        public Method getMethod() {
            return method;
        }

        @Override
        public Class[] getParameterTypes() {
            return method.getParameterTypes();
        }

        @Override
        public String[] getParameterNames() {
            String[] names = new String[method.getParameters().length];
            return Arrays.stream(method.getParameters()).map(Parameter::toString).collect(toList()).toArray(names);
        }

        @Override
        public Class[] getExceptionTypes() {
            return method.getExceptionTypes();
        }

        @Override
        public String toShortString() {
            return getReturnType().getSimpleName()+" "+getDeclaringType().getName()+"."+method.getName()+Arrays.toString(Arrays.stream(getParameterTypes()).map(Class::getSimpleName).collect(toList()).toArray());
        }

        @Override
        public String toLongString() {
            return method.toString();
        }

        @Override
        public String getName() {
            return this.toLongString();
        }

        @Override
        public int getModifiers() {
            return method.getModifiers();
        }

        @Override
        public Class getDeclaringType() {
            return method.getDeclaringClass();
        }

        @Override
        public String getDeclaringTypeName() {
            return this.getDeclaringType().getName();
        }

        @Override
        public String toString() {
            return "hit cache == "+this.toShortString();
        }
    }

}
