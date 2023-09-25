package com.codingtu.cooltu.lib4a.path;

import com.codingtu.cooltu.lib4j.data.bean.CoreBean;

import java.io.File;

public class BasePath extends CoreBean {

    public static final String SEPARATOR = File.separator;

    protected static String addPrexSeparator(String dir) {
        return SEPARATOR + dir;
    }

    protected String root;

    public BasePath(String root) {
        this.root = root;
    }

    public String root(){
        return this.root;
    }
}
