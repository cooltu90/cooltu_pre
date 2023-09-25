package com.codingtu.cooltu.lib4a.path;

import com.codingtu.cooltu.lib4j.json.JsonTool;

public class PathBeanFile<T> extends PathTextFile {

    private final Class<T> beanClass;

    public PathBeanFile(String root, String type, Class beanClass) {
        super(root, type);
        this.beanClass = beanClass;
    }

    public T bean() {
        return JsonTool.toBean(beanClass, getText());
    }

    public void bean(T bean) {
        setText(JsonTool.toJson(bean));
    }
}
