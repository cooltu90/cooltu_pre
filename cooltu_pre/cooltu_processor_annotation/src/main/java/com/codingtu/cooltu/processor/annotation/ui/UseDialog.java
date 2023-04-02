package com.codingtu.cooltu.processor.annotation.ui;

public @interface UseDialog {
    String[] names() default {"dialog"};

    String[] titles();

    String[] contents();

    Class[] objTypes() default {Object.class};
}
