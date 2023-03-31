package core.processor.lib.bean;

import javax.lang.model.element.Element;

import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.StringTool;
import core.constant.FieldName;
import core.processor.annotation.form.FormRadioGroup;

public class FromItemInfoForRg extends FormItemInfo {
    public String onSetItemClass;
    public String onSetItemName;
    public boolean hasOnSetItem;

    public FromItemInfoForRg(Element element) {
        super();
        FormRadioGroup formRadioGroup = element.getAnnotation(FormRadioGroup.class);
        if (formRadioGroup != null) {
            onSetItemClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return formRadioGroup.onSetItem();
                }
            });
            hasOnSetItem = !ClassTool.isVoid(onSetItemClass);
            if (hasOnSetItem) {
                onSetItemName = formRadioGroup.onSetItemName();
                if (StringTool.isBlank(onSetItemName)) {
                    onSetItemName = FieldName.DEFAULT_ON_SET_ITEM;
                }
            }
        }

    }
}
