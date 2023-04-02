package com.codingtu.cooltu.processor.worker.model.base;

import java.util.List;

import cooltu.lib4j.data.bean.JavaInfo;

public abstract class BaseAdapterModel extends BaseModel {

    protected List<String> beans;
    protected String adapter;
    protected String rvName;
    protected String uiType;
    protected String adapterName;

    public BaseAdapterModel() {
        super(null);
    }

    public void setBeans(List<String> beanClasses) {
        this.beans = beanClasses;
    }

    public void setInfo(JavaInfo info) {
        this.info = info;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }

    public void setRvName(String rvName) {
        this.rvName = rvName;
    }

    public abstract StringBuilder getOthers(JavaInfo baseInfo);

    public abstract StringBuilder getOnCreates(JavaInfo baseInfo);

    public abstract StringBuilder getOnDestorys(JavaInfo baseInfo);

    public void setUiType(String uiType) {
        this.uiType = uiType;
    }

    public void setAdapterName(String adapterName) {
        this.adapterName = adapterName;
    }
}
