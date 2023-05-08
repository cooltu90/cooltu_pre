package com.codingtu.cooltu.processor.annotation.form;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface FormParse {
    //如果控件数据类型和实体类数据类型不同时，需要进行转换，此处设置转换器。
    Class value() default Void.class;
}
