package core.processor.lib.tools;

import javax.lang.model.element.ExecutableElement;

import core.processor.lib.bean.Param;

public class ParamTools {

    public static Param getDefaultParam(ExecutableElement element) {
        return new Param(element.getParameters(), null);
    }

}
