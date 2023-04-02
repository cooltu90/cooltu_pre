package com.codingtu.cooltu.processor.annotation.create;

import com.codingtu.cooltu.constant.AdapterType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface CreateAdapter {
    String name();

    String packages();

    AdapterType type();

    Class[] beanClasses();
}
