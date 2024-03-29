package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;

import com.codingtu.cooltu.constant.Pkg;

import java.util.List;

import com.codingtu.cooltu.processor.modelinterface.CreateFragmentModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

public class CreateFragmentModel extends BaseModel implements CreateFragmentModelInterface {
    private final String layoutBaseName;
    private final JavaInfo fragmentBaseInfo;

    public CreateFragmentModel(JavaInfo info, String layoutBaseName, JavaInfo fragmentBaseInfo) {
        super(info);
        this.layoutBaseName = layoutBaseName;
        this.fragmentBaseInfo = fragmentBaseInfo;
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    /***************************************
     *
     *
     *
     ***************************************/
    @Override
    public void setTagFor_pkg(StringBuilder sb) {
        addTag(sb, info.pkg);
    }

    @Override
    public void setTagFor_rPkg(StringBuilder sb) {
        addTag(sb, Pkg.DEFAULT_R);
    }

    @Override
    public void setTagFor_imports(StringBuilder sb) {
        addTag(sb, "import " + fragmentBaseInfo.fullName + ";");
    }

    @Override
    public void setTagFor_resPkg(StringBuilder sb) {
        addTag(sb, Pkg.FRAGMENT_RES);
    }

    @Override
    public void setTagFor_baseName(StringBuilder sb) {
        addTag(sb, layoutBaseName);
    }

    @Override
    public void setTagFor_name(StringBuilder sb) {
        addTag(sb, info.name);
    }

    @Override
    public void setTagFor_base(StringBuilder sb) {
        addTag(sb, fragmentBaseInfo.name);
    }
}
/* model_temp_start
package [[pkg]];

import [[rPkg]].R;
[[imports]]
import [[resPkg]].[[name]]Res;
import com.codingtu.cooltu.processor.annotation.tools.To;

import com.codingtu.cooltu.processor.annotation.ui.FragmentBase;

@To([[name]]Res.class)
@FragmentBase(layout = R.layout.fragment_[[baseName]])
public class [[name]] extends [[base]] {

}
model_temp_end */
