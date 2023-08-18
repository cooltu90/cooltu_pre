package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.Pkg;

import java.util.List;

import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.modelinterface.AdapterDefaultModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.BaseAdapterModel;

public class AdapterDefaultModel extends BaseAdapterModel implements AdapterDefaultModelInterface {

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    public void beforCreate() {
        addTag("pkg", info.pkg);
        addTag("rPkg", Pkg.DEFAULT_R);

        String bean = this.beans.get(0);
        addTag("bean", bean);
        String typeBaseName = NameTools.getAdapterTypeBaseName(info.name);
        JavaInfo vhInfo = NameTools.getVHInfo(typeBaseName);
        addTag("vhFullName", vhInfo.fullName);
        String layoutBaseName = ConvertTool.toLayoutType(typeBaseName);
        addTag("layoutName", NameTools.getItemLayoutName(layoutBaseName));
        addTag("name", info.name);
        addTag("vhName", vhInfo.name);

        JavaInfo beanInfo = NameTools.getJavaInfoByName(bean);
        addTag("beanTypeName", beanInfo.name);
    }

    @Override
    public StringBuilder getOthers(JavaInfo baseInfo) {
        StringBuilder sb = getTag("forUiOthers");
        addLnTag(sb, "    protected [type] [adapter];", adapter, adapterName);
        return sb;
    }

    @Override
    public StringBuilder getOnCreates(JavaInfo baseInfo) {
        StringBuilder sb = getTag("forUiOnCreates");
        JavaInfo adapterInfo = NameTools.getJavaInfoByName(adapter);
        JavaInfo vhInfo = NameTools.adapterToVH(adapterInfo);
        addLnTag(sb, "        [adapter] = new [type]();", adapterName, adapter);
        addLnTag(sb, "        [adapter].setVH([vh].class);", adapterName, vhInfo.fullName);
        addLnTag(sb, "        [adapter].setClick(this);", adapterName);
        addLnTag(sb, "        [rv].setAdapter([adapter]);", rvName, adapterName);
        addLnTag(sb, "        [rv].setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(com.codingtu.cooltu.lib4a.CoreApp.APP));", rvName);
        return sb;
    }

    @Override
    public StringBuilder getOnDestorys(JavaInfo baseInfo) {
        return new StringBuilder();
    }

    @Override
    public void setTagFor_pkg(StringBuilder sb) {

    }

    @Override
    public void setTagFor_rPkg(StringBuilder sb) {

    }

    @Override
    public void setTagFor_bean(StringBuilder sb) {

    }

    @Override
    public void setTagFor_vhFullName(StringBuilder sb) {

    }

    @Override
    public void setTagFor_layoutName(StringBuilder sb) {

    }

    @Override
    public void setTagFor_name(StringBuilder sb) {

    }

    @Override
    public void setTagFor_vhName(StringBuilder sb) {

    }

    @Override
    public void setTagFor_beanTypeName(StringBuilder sb) {

    }
}
/* model_temp_start
package [[pkg]];

import [[rPkg]].R;
import [[bean]];

import com.codingtu.cooltu.lib4a.act.adapter.CoreListAdapter;
import com.codingtu.cooltu.processor.annotation.ui.VH;
import [[vhFullName]];

@VH(R.layout.[[layoutName]])
public class [[name]] extends CoreListAdapter<[[vhName]], [[beanTypeName]]> {
    @Override
    protected void onBindVH([[vhName]] vh, int position, [[beanTypeName]] t) {

    }
}
model_temp_end */
