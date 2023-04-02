package com.codingtu.cooltu.processor.annotation.net;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Param {
    String value() default "";

    boolean encoded() default false;

    int type() default ParamType.DEFAULT;

    String defaultValue() default "";


}
