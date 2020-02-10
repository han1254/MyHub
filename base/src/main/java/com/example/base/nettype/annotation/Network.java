package com.example.base.nettype.annotation;


import com.example.base.nettype.type.NetType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author YangZhaoxin.
 * @since 2019/9/28 20:57.
 * email yangzhaoxin@hrsoft.net.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Network {
    NetType netType() default NetType.AUTO;
}
