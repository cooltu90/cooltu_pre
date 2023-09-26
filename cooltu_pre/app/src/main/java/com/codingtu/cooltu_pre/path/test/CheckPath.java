package com.codingtu.cooltu_pre.path.test;

import com.codingtu.cooltu.lib4a.path.BasePath;
import com.codingtu.cooltu.lib4a.tools.SDCardTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu_pre.path.filter.LabelFilter;

import java.io.File;
import java.util.List;

import core.path.CheckLabelPath;


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

    public CheckLabelPath label(String labelName) {
        return new CheckLabelPath(
                this.root
                        + addPrexSeparator(labelName)
        );
    }

    public List<CheckLabelPath> labelList(String type) {
        LabelFilter labelFilter = new LabelFilter();
        return Ts.ts(new File(root).listFiles()).convert((index, file) -> {
            if (labelFilter.check(file.getName())) {
                return label(file.getName());
            }
            return null;
        }).get();
    }

}
