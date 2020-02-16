package com.example.myhub.mvvm.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Time:2020/2/11 21:29
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Named {
    String value() default "";
}