package core.processor.lib.form;

public interface FormLink {

    FormLink setTarget(Object target);

    void link();

    FormLink setSrcs(Object... srcs);

    FormLink setBean(Object bean);
}
