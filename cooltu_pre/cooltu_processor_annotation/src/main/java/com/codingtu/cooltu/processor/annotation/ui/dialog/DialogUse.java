package com.codingtu.cooltu.processor.annotation.ui.dialog;

public @interface DialogUse {
    String title();

    String content();

    Class objType() default Object.class;

    String leftBtText() default "取消";

    String rightBtText() default "确定";
}
