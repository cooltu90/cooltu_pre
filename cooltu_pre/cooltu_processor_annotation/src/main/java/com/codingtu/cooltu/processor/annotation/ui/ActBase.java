package com.codingtu.cooltu.processor.annotation.ui;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ActBase {
    int layout();

    Class baseClass() default Void.class;

    Class[] fanxing() default {};

    boolean withoutLayout() default false;
}
