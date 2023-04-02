package com.codingtu.cooltu.lib4a.form;

public interface FormLink {

    FormLink setTarget(Object target);

    void link();

    FormLink setSrcs(Object... srcs);

    FormLink setBean(Object bean);
}
