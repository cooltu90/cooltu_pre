package com.codingtu.cooltu.processor.annotation.ui;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface DefaultPass {
    Class[] paramClasses() default {};

    String[] paramNames() default {};

    Class value() default Void.class;

    boolean isParams() default false;
}

