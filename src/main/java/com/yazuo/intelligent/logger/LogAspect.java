package com.yazuo.intelligent.logger;

import com.alibaba.fastjson.JSON;
import com.yazuo.intelligent.logger.exception.AbstractException;
import com.yazuo.intelligent.logger.exception.utils.ExceptionUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Around("@annotation(apiOperation)")
    public Object log(ProceedingJoinPoint point, ApiOperation apiOperation) throws Throwable {
        long start = System.currentTimeMillis();
        Object[] args = Arrays.stream(point.getArgs())
                .filter(o-> !(o instanceof HttpServletRequest))
                .filter(o-> !(o instanceof HttpServletResponse))
                .filter(o-> !(o instanceof File))
                .filter(o-> !(o instanceof HttpSession))
                .filter(o-> !(o instanceof MultipartFile)).toArray();
        String params = JSON.toJSONString(args);
        String methodName = point.getSignature().toString();

        try {
            Object result = point.proceed();
            ExceptionUtils.logInfo(log,System.currentTimeMillis()-start,methodName,apiOperation.value(),params,200,JSON.toJSONString(result));
           return result;
        } catch (Throwable throwable) {
            throwable.getMessage();
            int code = 500;
            if(throwable instanceof AbstractException){
                AbstractException exception = (AbstractException) throwable;
                code = exception.getCode();
            }
            ExceptionUtils.logError(log,System.currentTimeMillis()-start,methodName,apiOperation.value(),params,code,throwable);

            throw throwable;
        }

    }
}
