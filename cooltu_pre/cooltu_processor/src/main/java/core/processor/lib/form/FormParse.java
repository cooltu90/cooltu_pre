package core.processor.lib.form;

public interface FormParse<Bean, View> {

    public View toView(Bean bean);

    public Bean toBean(Object obj);

}
