package com.codingtu.cooltu.processor.lib.bean;

import java.util.Map;

import com.codingtu.cooltu.processor.lib.tools.IdTools;

public class FormItemInfo {
    public String beanField;
    public String prompt;
    public String parse = Void.class.getCanonicalName();
    public String check = Void.class.getCanonicalName();

    public IdTools.Id viewId;
    public String viewName;
    public String fieldName;
    public String fieldType;
    public int formItemType;

    //link相关
    public Map<Integer, IdTools.Id> idMap;
    public int[] ids;
    public String linkClass;
}
