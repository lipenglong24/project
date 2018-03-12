package com.lipenglong.lspring.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * service bean注解
 * User: lipl
 * Date: 12-8-7
 * Time: 下午4:31
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    String name() default "";
}
