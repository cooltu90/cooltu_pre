package com.codingtu.cooltu.processor.worker.deal;

import com.codingtu.cooltu.processor.annotation.resource.ColorRes;
import com.codingtu.cooltu.processor.annotation.resource.ColorStr;
import com.codingtu.cooltu.processor.annotation.resource.Dimen;
import com.codingtu.cooltu.processor.annotation.resource.Dp;
import com.codingtu.cooltu.processor.annotation.ui.InBase;
import com.codingtu.cooltu.processor.annotation.ui.InBaseClickView;
import com.codingtu.cooltu.processor.annotation.ui.StartGroup;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.worker.deal.base.BaseDeal;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;

public class ResForBaseDeal extends BaseDeal {

    public static ListValueMap<String, String> baseMap = new ListValueMap<>();
    public static ListValueMap<String, String> inBaseMap = new ListValueMap<>();
    public static ListValueMap<String, VariableElement> startGroupMap = new ListValueMap<>();
    public static ListValueMap<String, VariableElement> colorStrMap = new ListValueMap<>();
    public static ListValueMap<String, VariableElement> colorResMap = new ListValueMap<>();
    public static ListValueMap<String, VariableElement> dpMap = new ListValueMap<>();
    public static ListValueMap<String, VariableElement> dimenMap = new ListValueMap<>();
    public static ListValueMap<String, ExecutableElement> clickViewMap = new ListValueMap<>();

    @Override
    public void deal(Element element) {
        if (!(element instanceof TypeElement)) {
            return;
        }

        TypeElement te = (TypeElement) element;

        String classFullName = ElementTools.getType(te);

        String parentClass = te.getSuperclass().toString();

        baseMap.get(classFullName).add(parentClass);

        Ts.ls(te.getEnclosedElements(), new Each<Element>() {
            @Override
            public boolean each(int position, Element element) {
                if (element instanceof VariableElement) {
                    VariableElement ve = (VariableElement) element;
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
                }

                if (element instanceof ExecutableElement) {
                    InBaseClickView clickView = element.getAnnotation(InBaseClickView.class);
                    if (clickView != null) {
                        clickViewMap.get(classFullName).add((ExecutableElement) element);
                    }
                }

                return false;
            }
        });

    }

    public static <T> List<T> getTs(ListValueMap<String, T> map, String baseClass) {
        ArrayList<T> ts = new ArrayList<>();
        ts.addAll(map.get(baseClass));
        List<String> bases = baseMap.get(baseClass);
        int count = CountTool.count(bases);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                String base = bases.get(i);
                ts.addAll(getTs(map, base));
            }
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
