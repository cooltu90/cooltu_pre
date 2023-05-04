package com.codingtu.cooltu.processor.worker.deal;

import javax.lang.model.element.Element;

import cooltu.lib4j.data.bean.KV;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;

import com.codingtu.cooltu.processor.annotation.form.FormItem;
import com.codingtu.cooltu.processor.annotation.form.FormItemLink;
import com.codingtu.cooltu.processor.annotation.form.FormType;
import com.codingtu.cooltu.processor.lib.bean.FormItemInfo;
import com.codingtu.cooltu.processor.lib.bean.FromItemInfoForRg;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.model.BaseParentModel;

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
        info.check = ClassTool.getAnnotationClass(new ClassTool.AnnotationClassGetter() {
            @Override
            public Object get() {
                return formItem.check();
            }
        });

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
