package com.codingtu.cooltu.processor.annotation.ui.dialog;

public @interface DialogUse {
    String title();

    String content();

    Class objType() default Object.class;
}
