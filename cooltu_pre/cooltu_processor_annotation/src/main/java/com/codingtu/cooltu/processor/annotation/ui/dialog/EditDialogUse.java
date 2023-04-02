package com.codingtu.cooltu.processor.annotation.ui.dialog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface EditDialogUse {

    //标题
    String title();

    //输入框的提示文字
    String hint();

    //输入框的输入类型
    int inputType() default 1;

    //是否储存上次输入的值
    boolean reserve() default true;

    boolean stopAnimation() default false;

    //传递的值类型
    Class objType() default Void.class;

}
