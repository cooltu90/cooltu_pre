package com.codingtu.cooltu.processor.lib.bean;

import com.codingtu.cooltu.constant.FieldName;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.form.FormRadioGroup;
import com.codingtu.cooltu.processor.annotation.form.item.RadioGroupFormItem;

import javax.lang.model.element.Element;

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

    public FromItemInfoForRg(RadioGroupFormItem radioGroupFormItem) {
        super();
        onSetItemClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return radioGroupFormItem.onSetItem();
            }
        });
        hasOnSetItem = !ClassTool.isVoid(onSetItemClass);
        if (hasOnSetItem) {
            onSetItemName = radioGroupFormItem.onSetItemName();
            if (StringTool.isBlank(onSetItemName)) {
                onSetItemName = FieldName.DEFAULT_ON_SET_ITEM;
            }
        }

        if (!CountTool.isNull(radioGroupFormItem.strItems())) {
            parse = FullName.DEFAULT_RADIO_GROUP_TO_STRING;
            StringBuilder sb = new StringBuilder();
            Ts.ls(radioGroupFormItem.strItems(), new BaseTs.EachTs<String>() {
                @Override
                public boolean each(int position, String s) {
                    if (position != 0) {
                        sb.append(", ");
                    }
                    sb.append("\"").append(s).append("\"");
                    return false;
                }
            });
            parseParams = sb.toString();
        }

    }
}
