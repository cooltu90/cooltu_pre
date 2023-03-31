package core.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import cooltu.lib4j.tools.ClassTool;
import core.processor.annotation.form.Form;
import core.processor.lib.model.BaseParentMap;
import core.processor.worker.deal.base.BaseDeal;

public class FormDeal extends BaseDeal {
    public static BaseParentMap map = new BaseParentMap();


    @Override
    public void deal(Element element) {
        Form form = element.getAnnotation(Form.class);
        String formClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return form.value();
            }
        });
        map.add(formClass, (TypeElement) element);
    }

}
