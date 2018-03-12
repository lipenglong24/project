package com.lipenglong.lspring.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 持久层bean注解
 * User: lipl
 * Date: 12-8-7
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Persistance {
    String name() default "";
}
