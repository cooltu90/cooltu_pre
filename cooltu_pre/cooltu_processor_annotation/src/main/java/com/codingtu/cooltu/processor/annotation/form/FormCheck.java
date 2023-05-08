package com.codingtu.cooltu.processor.annotation.form;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface FormCheck {
    //具体的检测代码，不设置则按照String的默认检测处理（判空）。
    Class value() default Void.class;
}
