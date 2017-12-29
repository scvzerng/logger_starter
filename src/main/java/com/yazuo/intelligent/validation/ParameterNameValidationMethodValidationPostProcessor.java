package com.yazuo.intelligent.validation;

import org.aopalliance.aop.Advice;
import org.hibernate.validator.parameternameprovider.ReflectionParameterNameProvider;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.MethodValidationInterceptor;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.annotation.Annotation;

/**
 * description
 * <p>
 * 2017-12-29 13:34
 *
 * @author scvzerng
 **/
public class ParameterNameValidationMethodValidationPostProcessor extends MethodValidationPostProcessor {
    private Class<? extends Annotation> validatedAnnotationType = Validated.class;

    public ParameterNameValidationMethodValidationPostProcessor(Class<? extends Annotation> validatedAnnotationType) {
        this.validatedAnnotationType = validatedAnnotationType;
    }

    @Override
    public void afterPropertiesSet() {
        Pointcut pointcut = new AnnotationMatchingPointcut(null,this.validatedAnnotationType);
        this.advisor = new DefaultPointcutAdvisor(pointcut, createMethodValidationAdvice(null));
    }
    @Override
    protected Advice createMethodValidationAdvice(Validator validator) {
        return (validator != null ? new MethodValidationInterceptor() : new MethodValidationInterceptor(Validation.byDefaultProvider().configure().parameterNameProvider(new ReflectionParameterNameProvider()).buildValidatorFactory()));
    }
}
