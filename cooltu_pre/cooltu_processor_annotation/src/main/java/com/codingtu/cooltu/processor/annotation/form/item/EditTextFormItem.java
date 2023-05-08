package com.codingtu.cooltu.processor.annotation.form.item;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface EditTextFormItem {
    int value();

    String prompt() default "";
}
