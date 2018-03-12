package com.lipenglong.lspring.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 依赖注解
 * User: lipl
 * Date: 12-8-8
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    String name() default "";
}
