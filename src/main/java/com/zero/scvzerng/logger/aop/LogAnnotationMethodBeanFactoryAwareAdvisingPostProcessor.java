package com.zero.scvzerng.logger.aop;

import com.zero.scvzerng.logger.LogProperties;
import lombok.Data;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 * description
 * <p>
 * 2017-12-29 15:31
 *
 * @author scvzerng
 **/
@Data
public class LogAnnotationMethodBeanFactoryAwareAdvisingPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor implements InitializingBean {
   @Resource
   LogProperties logProperties;
   @Resource
   LogMethodInterceptor logMethodInterceptor;
    @Override
    public void afterPropertiesSet() throws Exception {
        Pointcut pointcut = new AnnotationMatchingPointcut(null,logProperties.getLogAnnotation());
        this.advisor = new DefaultPointcutAdvisor(pointcut,logMethodInterceptor);
    }
}
