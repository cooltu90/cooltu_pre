package core.processor.lib.tools;


import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.data.bean.KV;
import cooltu.lib4j.tools.StringTool;
import core.processor.annotation.form.FormBean;

public class ElementTools {

    public static String getType(Element e) {
        return e.asType().toString();
    }

    public static String getParentType(Element e) {
        return getType(e.getEnclosingElement());
    }

    public static String simpleName(Element e) {
        return e.getSimpleName().toString();
    }

    public static String staticSimpleName(Element e) {
        return StringTool.toStaticType(simpleName(e));
    }

    public static KV<String, String> getFiledKv(VariableElement ve) {
        return new KV<>(getType(ve), simpleName(ve));
    }

    public static KV<String, String> getFormBeanName(Element element) {
        FormBean formBean = element.getAnnotation(FormBean.class);
        String value = formBean.value();
        String type = ElementTools.getType(element);
        if (StringTool.isBlank(value)) {
            value = StringTool.toMethodType(NameTools.getJavaSimpleName(type));
        }
        return new KV<String, String>(type, value);
    }


}
