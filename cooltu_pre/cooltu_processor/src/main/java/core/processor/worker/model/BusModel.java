package core.processor.worker.model;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.data.bean.KV;
import cooltu.lib4j.tools.ClassTool;
import core.constant.Pkg;
import core.processor.modelinterface.BusModelInterface;
import core.processor.worker.model.base.BaseModel;

public class BusModel extends BaseModel implements BusModelInterface {
    private KV<String, String> kv;

    public BusModel(JavaInfo info) {
        super(info);
        isForce = true;
    }

    public void add(KV<String, String> kv) {
        this.kv = kv;
    }

    @Override
    public void setTagFor_pkg(StringBuilder sb) {
        addTag(sb, Pkg.BUS);
    }

    @Override
    public void setTagFor_baseName(StringBuilder sb) {
        addTag(sb, kv.v);
    }

    @Override
    public void setTagFor_readParams(StringBuilder sb) {
        if (!ClassTool.isVoid(kv.k)) {
            //有返回值
            addTag(sb, "([String]) obj", kv.k);
        }
    }

    @Override
    public void setTagFor_params(StringBuilder sb) {
        if (!ClassTool.isVoid(kv.k)) {
            //有返回值
            addTag(sb, "[k] data", kv.k);
        }
    }

    @Override
    public void setTagFor_name(StringBuilder sb) {
        addTag(sb, info.name);
    }
}
/* model_temp_start
package [[pkg]];

import core.lib4a.bus.Bus;

public abstract class [[name]] implements Bus {

    public static final String TASK = "[[baseName]]";

    @Override
    public String getTask() {
        return TASK;
    }

    @Override
    public void back(Object obj) {
        [[baseName]]BusBack([[readParams]]);
    }

    protected abstract void [[baseName]]BusBack([[params]]);

}
model_temp_end */
