package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.lib4j.data.bean.KV;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.processor.lib.ls.EachType;
import com.codingtu.cooltu.processor.lib.ls.TypeLs;
import com.codingtu.cooltu.processor.modelinterface.PassModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.SingleCoreToolsBaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassModel extends SingleCoreToolsBaseModel implements PassModelInterface {

    private Map<String, String> nameMap = new HashMap<>();
    private Map<String, String> nameMap1 = new HashMap<>();

    public static final PassModel model = new PassModel();
    private List<KV<String, String>> kvs = new ArrayList<>();

    public PassModel() {
        super(Constant.NAME_PASS);
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    public void add(List<String> classes, String[] paramNames) {
        TypeLs.ls(classes, paramNames, new EachType() {
            @Override
            public void each(int position, String type, String name) {
                add(type, name);
            }
        });
    }

    public void add(String type, String name) {
        add(new KV<>(type, name));
    }

    public void add(KV<String, String> kv) {
        kvs.add(kv);
    }

    @Override
    public void setTagFor_fields(StringBuilder fieldsSb) {
        Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                String value = nameMap.get(kv.v);
                if (StringTool.isNotBlank(value)) {
                    return false;
                }
                nameMap.put(kv.v, kv.v);

                addLnTag(fieldsSb,
                        "    public static final String [name] = \"[value]\";",
                        ConvertTool.toStaticType(kv.v), kv.v);
                return false;
            }
        });
    }

    @Override
    public void setTagFor_methods(StringBuilder methodsSb) {
        Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                String type = kv.k;
                String name = kv.v;

                String value = nameMap1.get(name);
                if (StringTool.isNotBlank(value)) {
                    return false;
                }
                nameMap1.put(name, name);

                String staticName = ConvertTool.toStaticType(name);
                String methodName = ConvertTool.toMethodType(name);

                if (ClassTool.isString(type)) {
                    addLnTag(methodsSb, "    public static final String [name](Intent data) {", methodName);
                    addLnTag(methodsSb, "        return data.getStringExtra([name]);", staticName);
                } else if (ClassTool.isInt(type) || ClassTool.isInteger(type)) {
                    addLnTag(methodsSb, "    public static final int [name](Intent data) {", methodName);
                    addLnTag(methodsSb, "        return data.getIntExtra([name], -1);", staticName);
                } else if (ClassTool.isDouble(type) || ClassTool.isDOUBLE(type)) {
                    addLnTag(methodsSb, "    public static final double [name](Intent data) {", methodName);
                    addLnTag(methodsSb, "        return data.getDoubleExtra([name], -1);", staticName);
                } else if (ClassTool.isFloat(type) || ClassTool.isFLOAT(type)) {
                    addLnTag(methodsSb, "    public static final float [name](Intent data) {", methodName);
                    addLnTag(methodsSb, "        return data.getFloatExtra([name], -1);", staticName);
                } else if (ClassTool.isLong(type) || ClassTool.isLONG(type)) {
                    addLnTag(methodsSb, "    public static final long [name](Intent data) {", methodName);
                    addLnTag(methodsSb, "        return data.getLongExtra([name], -1);", staticName);
                } else if (ClassTool.isBoolean(type) || ClassTool.isBOOLEAN(type)) {
                    addLnTag(methodsSb, "    public static final boolean [name](Intent data) {", methodName);
                    addLnTag(methodsSb, "        return data.getBooleanExtra([name], false);", staticName);
                } else if (ClassTool.isList(type)) {
                    String subType = StringTool.getSub(type, "List", "<", ">");
                    if (ClassTool.isInt(subType)) {
                        subType = Integer.class.getName();
                    } else if (ClassTool.isBoolean(subType)) {
                        subType = Boolean.class.getName();
                    } else if (ClassTool.isLong(subType)) {
                        subType = Long.class.getName();
                    } else if (ClassTool.isDouble(subType)) {
                        subType = Double.class.getName();
                    } else if (ClassTool.isFloat(subType)) {
                        subType = Float.class.getName();
                    }
                    addLnTag(methodsSb, "    public static final java.util.List<[subType]> [name](Intent data) {", subType, methodName);
                    addLnTag(methodsSb, "        return [JsonTools].toBeanList([class].class,data.getStringExtra([name]));",
                            FullName.JSON_TOOL, subType, staticName);
                } else {
                    addLnTag(methodsSb, "    public static final [n] [name](Intent data) {", type, methodName);
                    addLnTag(methodsSb, "        return [JsonTools].toBean([class].class,data.getStringExtra([name]));",
                            FullName.JSON_TOOL, type, staticName);


                }
                addLnTag(methodsSb, "    }");

                return false;
            }
        });
    }
}
/* model_temp_start
package core.tools;

import android.content.Intent;

public class Pass {
    public static final String FROM_ACT = com.codingtu.cooltu.constant.Constant.FROM_ACT;
[[fields]]

    public static final int fromAct(Intent data) {
        return data.getIntExtra(FROM_ACT, -1);
    }
[[methods]]

}
model_temp_end */
