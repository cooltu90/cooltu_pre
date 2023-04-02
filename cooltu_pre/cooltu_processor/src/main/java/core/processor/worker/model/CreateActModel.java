package core.processor.worker.model;

import cooltu.lib4j.data.bean.JavaInfo;

import com.codingtu.cooltu.constant.Pkg;

import java.util.List;

import core.processor.modelinterface.CreateActModelInterface;
import core.processor.worker.model.base.BaseModel;

public class CreateActModel extends BaseModel implements CreateActModelInterface {
    private String layoutBaseName;
    private JavaInfo actBaseInfo;

    public CreateActModel(JavaInfo info, String layoutBaseName, JavaInfo actBaseInfo) {
        super(info);
        this.layoutBaseName = layoutBaseName;
        this.actBaseInfo = actBaseInfo;
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
        addTag(sb, "import " + actBaseInfo.fullName + ";");
    }

    @Override
    public void setTagFor_resPkg(StringBuilder sb) {
        addTag(sb, Pkg.ACT_RES);
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
        addTag(sb, actBaseInfo.name);
    }

}
/* model_temp_start
package [[pkg]];

import android.os.Bundle;

import androidx.annotation.Nullable;

import [[rPkg]].R;

[[imports]]
import core.processor.annotation.tools.To;
import core.processor.annotation.ui.ActBase;
import [[resPkg]].[[name]]Res;

@To([[name]]Res.class)
@ActBase(layout = R.layout.activity_[[baseName]])
public class [[name]] extends [[base]] {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
model_temp_end */
