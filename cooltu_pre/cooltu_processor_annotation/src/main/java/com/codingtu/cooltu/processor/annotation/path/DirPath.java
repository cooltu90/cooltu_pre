package com.codingtu.cooltu.processor.annotation.path;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface DirPath {
    String parent() default "root";

    String dirName() default "";

    String fieldName() default "";
}
