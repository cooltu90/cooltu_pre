package com.codingtu.cooltu.processor.annotation.form;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface FormItem {
    //绑定的控件id，必须的
    int viewId();

    //绑定的控件类型，默认是EditText，会根据不同的类型做不同的绑定处理。
    int type() default FormType.EDIT_TEXT;

    //检测不合格的提示文字。设置此参数后才会生成检测代码
    String prompt() default "";

    //具体的检测代码，不设置则按照String的默认检测处理（判空）。
    Class check() default Void.class;

    //如果控件数据类型和实体类数据类型不同时，需要进行转换，此处设置转换器。
    Class parse() default Void.class;

}
