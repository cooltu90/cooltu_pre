package com.codingtu.cooltu.processor.annotation.delete;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface DeleteAct {
    String name();

    String packages();
}
