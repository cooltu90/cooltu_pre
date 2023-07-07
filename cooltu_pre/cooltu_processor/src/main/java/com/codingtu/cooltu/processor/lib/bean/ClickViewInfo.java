package com.codingtu.cooltu.processor.lib.bean;

import com.codingtu.cooltu.processor.annotation.ui.ClickView;
import com.codingtu.cooltu.processor.annotation.ui.InBaseClickView;
import com.codingtu.cooltu.processor.lib.tools.IdTools;

import java.util.Map;

import javax.lang.model.element.ExecutableElement;

public class ClickViewInfo {
    public int[] ids;
    public Map<Integer, IdTools.Id> idMap;
    public boolean inAct;
    public boolean check;
    public boolean checkLogin;
    public ExecutableElement element;

    public ClickViewInfo(ExecutableElement element, InBaseClickView clickView) {
        this.element = element;
        this.ids = clickView.value();
        this.idMap = IdTools.elementToIds(element, InBaseClickView.class, ids);
        this.inAct = clickView.inAct();
    }

    public ClickViewInfo(ExecutableElement element, ClickView clickView) {
        this.element = element;
        this.ids = clickView.value();
        this.idMap = IdTools.elementToIds(element, ClickView.class, ids);
        this.inAct = clickView.inAct();
        this.check = clickView.check();
        this.checkLogin = clickView.checkLogin();
    }

}
