package com.zero.scvzerng.logger;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LoggerFilter {
    String[] keys() default {};
    Class[] types() default {};
}
