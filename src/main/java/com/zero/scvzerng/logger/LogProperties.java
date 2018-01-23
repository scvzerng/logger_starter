package com.zero.scvzerng.logger;

import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.lang.annotation.Annotation;

/**
 * description
 * <p>
 * 2017-12-29 15:33
 *
 * @author scvzerng
 **/
@ConfigurationProperties(prefix = "log")
@Data
public class LogProperties {
    /**
     * 日志注解
     */
    private Class<? extends Annotation> logAnnotation = ApiOperation.class;
    /**
     * 过滤日志注解
     */
    private Class<? extends Annotation> filterAnnotation = LoggerFilter.class;

}
