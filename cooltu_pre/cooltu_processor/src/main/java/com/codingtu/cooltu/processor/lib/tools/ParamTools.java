package com.codingtu.cooltu.processor.lib.tools;

import javax.lang.model.element.ExecutableElement;

import com.codingtu.cooltu.processor.lib.bean.Param;

public class ParamTools {

    public static Param getDefaultParam(ExecutableElement element) {
        return new Param(element.getParameters(), null);
    }

}
