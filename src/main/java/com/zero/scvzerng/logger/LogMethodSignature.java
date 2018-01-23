package com.zero.scvzerng.logger;

import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * description
 * <p>
 * 2017-12-29 15:49
 *
 * @author scvzerng
 **/
public class LogMethodSignature implements MethodSignature {
    Method method;

    public LogMethodSignature(Method method) {
        this.method = method;
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
        return (String[]) Arrays.stream(method.getParameters()).map(Parameter::getName).toArray();
    }

    @Override
    public Class[] getExceptionTypes() {
        return method.getExceptionTypes();
    }

    @Override
    public String toShortString() {
        return method.toGenericString();
    }

    @Override
    public String toLongString() {
        return method.toString();
    }

    @Override
    public String getName() {
        return method.getName();
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
        return getDeclaringType().getName() ;
    }

    @Override
    public String toString() {
        return this.toLongString();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null) return false;
        if(obj!=this) return false;
        if(!obj.getClass().isAssignableFrom(LogMethodSignature.class)) return false;
        return obj.toString().equals(this.toString());
    }
}
