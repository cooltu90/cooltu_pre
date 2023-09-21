package com.codingtu.cooltu.processor.annotation.path;

import com.codingtu.cooltu.constant.PathType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface Path {
    String value() default "{root}";

    String fileType() default "";
}
