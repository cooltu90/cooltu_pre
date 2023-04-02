package com.codingtu.cooltu.processor.worker.model;

import cooltu.lib4j.data.bean.JavaInfo;

import com.codingtu.cooltu.constant.FullName;

import java.util.List;

import com.codingtu.cooltu.processor.modelinterface.ResModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

public class ResModel extends BaseModel implements ResModelInterface {
    private final JavaInfo actInfo;

    public ResModel(JavaInfo info, JavaInfo actInfo) {
        super(info);
        this.actInfo = actInfo;
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public void setTagFor_pkg(StringBuilder sb) {
        addTag(sb, info.pkg);
    }

    @Override
    public void setTagFor_actFullName(StringBuilder sb) {
        addTag(sb, actInfo.fullName);
    }

    @Override
    public void setTagFor_resFullName(StringBuilder sb) {
        addTag(sb, FullName.RES);
    }

    @Override
    public void setTagFor_actSimpleName(StringBuilder sb) {
        addTag(sb, actInfo.name);
    }
}
/* model_temp_start
package [[pkg]];

import [[actFullName]];
import [[resFullName]];

@ActRes([[actSimpleName]].class)
public class [[actSimpleName]]Res {

}

model_temp_end */
