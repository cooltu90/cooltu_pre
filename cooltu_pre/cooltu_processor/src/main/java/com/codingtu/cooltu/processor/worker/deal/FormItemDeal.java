package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.processor.annotation.form.FormCheck;
import com.codingtu.cooltu.processor.annotation.form.FormItem;
import com.codingtu.cooltu.processor.annotation.form.FormItemLink;
import com.codingtu.cooltu.processor.annotation.form.FormParse;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.lib.bean.FormItemInfo;
import com.codingtu.cooltu.processor.lib.bean.FromItemInfoForRg;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.BaseParentModel;

import javax.lang.model.element.Element;

import com.codingtu.cooltu.lib4j.data.bean.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.each.Each;

public class FormItemDeal extends BaseDeal {

    @Override
    public void deal(Element element) {
        FormItem formItem = element.getAnnotation(FormItem.class);
        FormItemInfo info = null;
        if (formItem.type() == FormType.RADIO_GROUP) {
            info = new FromItemInfoForRg(element);
        } else {
            info = new FormItemInfo();
        }

        String fieldType = ElementTools.getType(element);
        String fieldName = ElementTools.simpleName(element);

        Element parentElement = element.getEnclosingElement();
        KV<String, String> parentKv = ElementTools.getFormBeanName(parentElement);

        info.beanField = parentKv.v + "." + fieldName;
        info.fieldType = fieldType;
        info.prompt = formItem.prompt();
        info.parse = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return formItem.parse();
            }
        });

        FormParse formParse = element.getAnnotation(FormParse.class);
        if (formParse != null) {
            info.parse = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return formParse.value();
                }
            });
        }

        info.check = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return formItem.check();
            }
        });
        FormCheck formCheck = element.getAnnotation(FormCheck.class);
        if (formCheck != null) {
            info.check = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
                @Override
                public Object get() {
                    return formCheck.value();
                }
            });
        }

        IdTools.Id id = IdTools.elementToId(element, FormItem.class, formItem.viewId());


        info.viewId = id;
        info.viewName = id.rName;
        info.fieldName = id.rName;
        info.formItemType = formItem.type();
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
