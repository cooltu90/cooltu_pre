package com.codingtu.cooltu_pre.path.test;

import com.codingtu.cooltu.lib4a.path.BasePath;
import com.codingtu.cooltu.lib4a.tools.SDCardTool;


public class CheckPath extends BasePath {

    public CheckDeleteLabelPath DeleteLabel;

    public static CheckPath obtain(String company, String taskName) {
        return root(SDCardTool.getSDCard()
                + addPrexSeparator("EnvCheckData")
                + addPrexSeparator("tasks")
                + addPrexSeparator(company)
                + addPrexSeparator(taskName)
        );
    }

    public static CheckPath root(String root) {
        return new CheckPath(root);
    }

    public CheckPath(String root) {
        super(root);
        this.DeleteLabel = new CheckDeleteLabelPath(
                this.root
                        + addPrexSeparator("DeleteLabel")
        );
    }

}
