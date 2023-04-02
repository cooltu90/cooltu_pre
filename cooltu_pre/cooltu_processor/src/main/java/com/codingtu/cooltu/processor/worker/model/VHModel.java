package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.Pkg;

import java.util.List;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.tools.ConvertTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import com.codingtu.cooltu.processor.lib.tools.IdTools;
import com.codingtu.cooltu.processor.lib.tools.LayoutTools;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.modelinterface.VHModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.BaseModel;

public class VHModel extends BaseModel implements VHModelInterface {
    private String layoutName;
    private IdTools.Id layoutId;
    private List<LayoutTools.ViewInfo> vis;

    public VHModel(JavaInfo info, String typeBaseName, boolean isForce) {
        super(info);
        layoutName = NameTools.getItemLayoutName(ConvertTool.toLayoutType(typeBaseName));
        layoutId = new IdTools.Id(Pkg.DEFAULT_R, Constant.R_TYPE_LAYOUT, layoutName);
        vis = LayoutTools.read(layoutId.rName, null);
        this.isForce = isForce;
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    /**************************************************
     *
     * 设置tag的方法
     *
     **************************************************/
    @Override
    public void setTagFor_fileds(StringBuilder fieldSb) {
        Ts.ls(vis, new Each<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo vi) {
                addLnTag(fieldSb, "    public [n1] [n2];", vi.name, LayoutTools.getViFieldName(vi));
                return false;
            }
        });
    }

    @Override
    public void setTagFor_vhPkg(StringBuilder sb) {
        addTag(sb, info.pkg);
    }

    @Override
    public void setTagFor_name(StringBuilder sb) {
        addTag(sb, info.name);
    }

    @Override
    public void setTagFor_findView(StringBuilder findViewSb) {
        Ts.ls(vis, new Each<LayoutTools.ViewInfo>() {
            @Override
            public boolean each(int position, LayoutTools.ViewInfo vi) {
                String viParent = LayoutTools.getViParent(vi);
                boolean isCoreR = vi.id.startsWith("core_");
                addLnTag(findViewSb, "        [n1] = [n2]findViewById([n3].R.id.[n4]);",
                        LayoutTools.getViFieldName(vi),
                        StringTool.isNotBlank(viParent) ? (viParent + ".") : "itemView.",
                        isCoreR ? Pkg.COOLTU_LIB4A : layoutId.rPackage,
                        vi.id);
                return false;
            }
        });
    }

    @Override
    public void setTagFor_rPkg(StringBuilder sb) {
        addTag(sb, Pkg.DEFAULT_R);
    }

    @Override
    public void setTagFor_layoutName(StringBuilder sb) {
        addTag(sb, layoutName);
    }
}
/* model_temp_start
package [[vhPkg]];

import android.view.ViewGroup;

import com.codingtu.cooltu.lib4a.act.viewholder.CoreAdapterVH;

public class [[name]] extends CoreAdapterVH {
[[fileds]]

    public [[name]](ViewGroup parent) {
        super([[rPkg]].R.layout.[[layoutName]], parent);
[[findView]]
    }
}
model_temp_end */
