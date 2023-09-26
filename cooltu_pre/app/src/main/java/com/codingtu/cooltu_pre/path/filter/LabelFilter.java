package com.codingtu.cooltu_pre.path.filter;

import com.codingtu.cooltu.lib4a.path.BasePathFilter;
import com.codingtu.cooltu.processor.annotation.path.PathFilter;

@PathFilter
public class LabelFilter extends BasePathFilter {

    public String type;

    @Override
    public boolean check(String name) {
        return name.startsWith("L-");
    }
}
