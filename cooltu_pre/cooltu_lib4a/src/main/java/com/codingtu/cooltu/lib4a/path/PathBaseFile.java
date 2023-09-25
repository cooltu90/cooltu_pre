package com.codingtu.cooltu.lib4a.path;

public class PathBaseFile {

    protected String root;

    protected String type;

    public PathBaseFile(String root, String type) {
        this.root = root;
        this.type = type;
    }

    public String root() {
        return this.root;
    }

    public String type() {
        return type;
    }
}
