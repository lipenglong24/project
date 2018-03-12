package com.lipenglong.lspring.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Action注解
 * User: lipl
 * Date: 12-8-7
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String name() default "";
}
