package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.data.bean.KV;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;

import com.codingtu.cooltu.constant.FieldName;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.processor.annotation.form.FormCheck;
import com.codingtu.cooltu.processor.annotation.form.FormItem;
import com.codingtu.cooltu.processor.annotation.form.FormItemLink;
import com.codingtu.cooltu.processor.annotation.form.FormParse;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.annotation.form.item.EditTextFormItem;
import com.codingtu.cooltu.processor.annotation.form.item.SeekBarFormItem;
import com.codingtu.cooltu.processor.annotation.form.item.TextViewFormItem;
import com.codingtu.cooltu.processor.annotation.form.item.RadioGroupFormItem;
import com.codingtu.cooltu.processor.lib.bean.FormItemInfo;
import com.codingtu.cooltu.processor.lib.bean.FromItemInfoForRg;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.BaseParentModel;

public class FormBeanDeal extends BaseDeal {

    @Override
    public void deal(Element element) {
        KV<String, String> kv = ElementTools.getFormBeanName(element);
        Ts.ls(FormDeal.map.get(kv.k), new Each<BaseParentModel>() {
            @Override
            public boolean each(int position, BaseParentModel baseParentModel) {
                baseParentModel.addFormBean(kv.k, kv.v);
                baseParentModel.addField(FullName.BOOL, FieldName.INIT_FORM_BEAN);
                return false;
            }
        });

        Ts.ls(element.getEnclosedElements(), new Each<Element>() {
            @Override
            public boolean each(int position, Element element) {
                if (element instanceof VariableElement) {
                    dealElement((VariableElement) element);
                }
                return false;
            }
        });
    }

    public void dealElement(VariableElement ve) {
        EditTextFormItem formEditText = ve.getAnnotation(EditTextFormItem.class);
        if (formEditText != null) {
            dealEditText(ve, formEditText.prompt(), formEditText.value(),
                    FormType.EDIT_TEXT, formEditText.echoCheck());
        }

        TextViewFormItem formTextView = ve.getAnnotation(TextViewFormItem.class);
        if (formTextView != null) {
            dealEditText(ve, formTextView.prompt(), formTextView.value(),
                    FormType.TEXT_VIEW, formTextView.echoCheck());
        }
        RadioGroupFormItem radioGroupFormItem = ve.getAnnotation(RadioGroupFormItem.class);
        if (radioGroupFormItem != null) {
            dealEditText(ve, radioGroupFormItem.prompt(), radioGroupFormItem.value(), FormType.RADIO_GROUP, radioGroupFormItem.echoCheck());
        }

        SeekBarFormItem seekBarFormItem = ve.getAnnotation(SeekBarFormItem.class);
        if (seekBarFormItem != null) {
            dealEditText(ve, seekBarFormItem.prompt(), seekBarFormItem.value(), FormType.SEEK_BAR, seekBarFormItem.echoCheck());
        }

    }

    private void dealEditText(VariableElement element,
                              String prompt, int resId, int formItemType, boolean echoCheck) {
        FormItemInfo info = null;
        if (formItemType == FormType.RADIO_GROUP) {
            info = new FromItemInfoForRg(element.getAnnotation(RadioGroupFormItem.class));
        } else {
            info = new FormItemInfo();
        }

        String fieldType = ElementTools.getType(element);
        String fieldName = ElementTools.simpleName(element);

        Element parentElement = element.getEnclosingElement();
        KV<String, String> parentKv = ElementTools.getFormBeanName(parentElement);

        info.beanField = parentKv.v + "." + fieldName;
        info.fieldType = fieldType;
        info.prompt = prompt;
        FormParse formParse = element.getAnnotation(FormParse.class);
        if (formParse != null) {
            info.parse = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return formParse.value();
                }
            });
        }
        FormCheck formCheck = element.getAnnotation(FormCheck.class);
        if (formCheck != null) {
            info.check = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return formCheck.value();
                }
            });
        }

        IdTools.Id id = IdTools.elementToId(element, FormItem.class, resId);


        info.viewId = id;
        info.viewName = id.rName;
        info.fieldName = id.rName;
        info.formItemType = formItemType;
        if (info.formItemType == FormType.RADIO_GROUP) {
            info.fieldName = ElementTools.simpleName(element) + "Rg";
        }
        String parentType = ElementTools.getParentType(element);

        FormItemLink formItemLink = element.getAnnotation(FormItemLink.class);
        if (formItemLink != null) {
            info.linkClass = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return formItemLink.link();
                }
            });
            info.ids = formItemLink.ids();
            if (!CountTool.isNull(info.ids)) {
                info.idMap = IdTools.elementToIds(element, FormItemLink.class, info.ids);
            }
        }

        info.echoCheck = echoCheck;

        FormItemInfo formItemInfo = info;

        Ts.ls(FormDeal.map.get(parentType), new Each<BaseParentModel>() {
            @Override
            public boolean each(int position, BaseParentModel baseParentModel) {
                baseParentModel.addFormItem(formItemInfo);
                return false;
            }
        });
    }
}
