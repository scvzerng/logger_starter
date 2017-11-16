package com.yazuo.intelligent.autoconfig;

import com.alibaba.fastjson.JSON;
import com.yazuo.intelligent.logger.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({Logger.class, JSON.class})
@Slf4j
public class LoggerAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ErrorLogger errorLogger(){
        return new DefaultErrorLogger();
    }
    @Bean
    @ConditionalOnMissingBean
    public InfoLogger infoLogger(){
        return new DefaultInfoLogger();
    }
}
