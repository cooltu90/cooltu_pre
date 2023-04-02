package com.codingtu.cooltu.processor.annotation.create;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface CreateFragment {
    String name();

    String packages();

    Class baseClass() default Void.class;
}
