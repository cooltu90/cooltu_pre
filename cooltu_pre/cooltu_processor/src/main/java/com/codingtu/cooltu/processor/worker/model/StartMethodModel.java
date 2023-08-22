package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.bean.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.lib.tools.RenameTools;
import com.codingtu.cooltu.processor.modelinterface.StartMethodModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.SubBaseModel;

import java.util.ArrayList;
import java.util.List;


public class StartMethodModel extends SubBaseModel implements StartMethodModelInterface {
    private String actFullName;
    private String actStaticName;
    private List<KV<String, String>> kvs = new ArrayList<>();

    public StartMethodModel(String actFullName, String actStaticName) {
        this.actFullName = actFullName;
        this.actStaticName = actStaticName;
    }

    public StartMethodModel(String actFullName, String actStaticName, List<KV<String, String>> kvs) {
        this.actFullName = actFullName;
        this.actStaticName = actStaticName;
        this.kvs = kvs;
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public void setTagFor_methodName(StringBuilder sb) {
        String fullName = RenameTools.lsActs(actFullName, (oldFullName, newFullName) -> {
            if (oldFullName.equals(actFullName)) {
                return newFullName;
            }
            return null;
        });
        addTag(sb, RenameTools.actFullNameToMethodName(fullName));
    }

    @Override
    public void setTagFor_methodParams(StringBuilder sb) {
        StringBuilder paramSb = new StringBuilder();
        if (!CountTool.isNull(kvs)) {
            Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
                @Override
                public boolean each(int position, KV<String, String> kv) {
                    if (position != 0) {
                        paramSb.append(",");
                    }
                    paramSb.append(kv.k).append(" ").append(kv.v);
                    return false;
                }
            });
        }
        String params = paramSb.toString();
        addTag(sb, (StringTool.isNotBlank(params) ? ", " : "") + params);
    }

    @Override
    public void setTagFor_actFullName(StringBuilder sb) {
        addTag(sb, RenameTools.lsActs(actFullName, (oldFullName, newFullName) -> {
            if (oldFullName.equals(actFullName)) {
                return newFullName;
            }
            return null;
        }));
    }

    @Override
    public void setTagFor_code(StringBuilder sb) {
        addTag(sb, RenameTools.lsActs(actStaticName, (oldFullName, newFullName) -> {
            if (actFullName.equals(oldFullName)) {
                return RenameTools.actFullNameToStaticName(newFullName);
            }
            return null;
        }));
    }

    @Override
    public void setTagFor_params(StringBuilder sb) {
        Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (ClassTool.isBaseClass(kv.k)) {
                    addLnTag(sb, "        intent.putExtra(Pass.[NAME], [name]);", ConvertTool.toStaticType(kv.v), kv.v);
                } else {
                    addLnTag(sb, "        intent.putExtra(Pass.[NAME], [JsonTools].toJson([name]));",
                            ConvertTool.toStaticType(kv.v), FullName.JSON_TOOL, kv.v);
                }
                return false;
            }
        });
    }

    @Override
    public void setTagFor_ActTools(StringBuilder sb) {
        addTag(sb, FullName.ACT_TOOL);
    }
}
/* model_temp_start
    public static final void [[methodName]](Activity act[[methodParams]]) {
        Intent intent = new Intent(act, [[actFullName]].class);
        intent.putExtra(Pass.FROM_ACT,Code4Request.[[code]]);
[[params]]
        [[ActTools]].startActivityForResult(act, intent, Code4Request.[[code]]);
    }
model_temp_end */
