package com.codingtu.cooltu.processor.annotation.form.item;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface RadioGroupFormItem {
    int value();

    String prompt() default "";

    Class onSetItem() default Void.class;

    String onSetItemName() default "";

    boolean echoCheck() default false;

    String[] strItems() default {};


}
