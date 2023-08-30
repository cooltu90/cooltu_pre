package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.annotation.resource.ColorRes;
import com.codingtu.cooltu.processor.annotation.resource.ColorStr;
import com.codingtu.cooltu.processor.annotation.resource.Dimen;
import com.codingtu.cooltu.processor.annotation.resource.Dp;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.InBaseActBack;
import com.codingtu.cooltu.processor.annotation.ui.InBaseClickView;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.annotation.ui.dialog.EditDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.NoticeDialogUse;
import com.codingtu.cooltu.processor.annotation.ui.dialog.ToastDialogUse;
import com.codingtu.cooltu.processor.lib.bean.EditDialogInfo;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;
import com.codingtu.cooltu.processor.worker.deal.base.BaseResForDeal;
import com.codingtu.cooltu.processor.worker.model.ActBackIntentModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class ResForBaseDeal extends BaseResForDeal {

    public static ListValueMap<String, String> baseMap = new ListValueMap<>();
    public static ListValueMap<String, String> inBaseMap = new ListValueMap<>();
    public static ListValueMap<String, VariableElement> startGroupMap = new ListValueMap<>();
    public static ListValueMap<String, VariableElement> colorStrMap = new ListValueMap<>();
    public static ListValueMap<String, VariableElement> colorResMap = new ListValueMap<>();
    public static ListValueMap<String, VariableElement> dpMap = new ListValueMap<>();
    public static ListValueMap<String, VariableElement> dimenMap = new ListValueMap<>();
    public static ListValueMap<String, EditDialogInfo> editDialogMap = new ListValueMap<>();
    public static ListValueMap<String, ExecutableElement> clickViewMap = new ListValueMap<>();
    public static ListValueMap<String, ExecutableElement> actBackMap = new ListValueMap<>();
    public static Map<String, String> toastDialogUseMap = new HashMap<>();
    public static Map<String, String> noticeDialogUseMap = new HashMap<>();

    @Override
    public void deal(Element element) {
        if (!(element instanceof TypeElement)) {
            return;
        }

        TypeElement te = (TypeElement) element;

        String classFullName = ElementTools.getType(te);

        String parentClass = te.getSuperclass().toString();

        baseMap.get(classFullName).add(parentClass);

        ToastDialogUse toastDialogUse = te.getAnnotation(ToastDialogUse.class);
        if (toastDialogUse != null) {
            toastDialogUseMap.put(classFullName, classFullName);
        }
        NoticeDialogUse noticeDialogUse = te.getAnnotation(NoticeDialogUse.class);
        if (noticeDialogUse != null) {
            noticeDialogUseMap.put(classFullName, classFullName);
        }

        Ts.ls(te.getEnclosedElements(), (position, element1) -> {
            if (element1 instanceof VariableElement) {
                VariableElement ve = (VariableElement) element1;
                StartGroup startGroup = ve.getAnnotation(StartGroup.class);
                if (startGroup != null) {
                    startGroupMap.get(classFullName).add(ve);
                    inBaseMap.get(classFullName).add(ElementTools.simpleName(ve));
                }

                InBase inBase = ve.getAnnotation(InBase.class);
                if (inBase != null) {
                    inBaseMap.get(classFullName).add(ElementTools.simpleName(ve));
                }

                ColorStr colorStr = ve.getAnnotation(ColorStr.class);
                if (colorStr != null) {
                    colorStrMap.get(classFullName).add(ve);
                    inBaseMap.get(classFullName).add(ElementTools.simpleName(ve));
                }

                ColorRes colorRes = ve.getAnnotation(ColorRes.class);
                if (colorRes != null) {
                    colorResMap.get(classFullName).add(ve);
                    inBaseMap.get(classFullName).add(ElementTools.simpleName(ve));
                }

                Dp dp = ve.getAnnotation(Dp.class);
                if (dp != null) {
                    dpMap.get(classFullName).add(ve);
                    inBaseMap.get(classFullName).add(ElementTools.simpleName(ve));
                }

                Dimen dimen = ve.getAnnotation(Dimen.class);
                if (dimen != null) {
                    dimenMap.get(classFullName).add(ve);
                    inBaseMap.get(classFullName).add(ElementTools.simpleName(ve));
                }

                EditDialogUse editDialogUse = ve.getAnnotation(EditDialogUse.class);
                if (editDialogUse != null) {
                    editDialogMap.get(classFullName).add(toEditDialogInfo(ve, editDialogUse));
                    inBaseMap.get(classFullName).add(ElementTools.simpleName(ve));
                }

            }

            if (element1 instanceof ExecutableElement) {
                InBaseClickView clickView = element1.getAnnotation(InBaseClickView.class);
                if (clickView != null) {
                    clickViewMap.get(classFullName).add((ExecutableElement) element1);
                }

                InBaseActBack actBack = element1.getAnnotation(InBaseActBack.class);
                if (actBack != null) {
                    actBackMap.get(classFullName).add((ExecutableElement) element1);
                    ActBackIntentModel.model.addInBase((ExecutableElement) element1);
                }
            }

            return false;
        });

    }

    public static <T> List<T> getTs(ListValueMap<String, T> map, String baseClass) {
        ArrayList<T> ts = new ArrayList<>();
        List<String> bases = baseMap.get(baseClass);
        int count = CountTool.count(bases);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String base = bases.get(i);
                ts.addAll(getTs(map, base));
            }
        }
        ts.addAll(map.get(baseClass));
        return ts;
    }

    public static <T> List<T> getTs(Map<String, T> map, String baseClass) {
        ArrayList<T> ts = new ArrayList<>();
        List<String> bases = baseMap.get(baseClass);
        int count = CountTool.count(bases);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String base = bases.get(i);
                ts.addAll(getTs(map, base));
            }
        }
        T t = map.get(baseClass);
        if (t != null) {
            ts.add(t);
        }
        return ts;
    }


    public static boolean isInBase(String type, String fieldName) {
        if (inBaseMap.get(type).contains(fieldName)) {
            return true;
        }
        List<String> bases = baseMap.get(type);
        int count = CountTool.count(bases);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String base = bases.get(i);
                if (isInBase(base, fieldName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
