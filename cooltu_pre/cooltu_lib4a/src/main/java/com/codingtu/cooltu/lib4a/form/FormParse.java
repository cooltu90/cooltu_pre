package com.codingtu.cooltu.lib4a.form;

public interface FormParse<Bean, View> {

    public View toView(Bean bean);

    public Bean toBean(Object obj);

}
