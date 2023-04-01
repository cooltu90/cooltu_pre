package core.processor.worker.model;

import java.util.HashMap;
import java.util.List;

import cooltu.lib4j.data.bean.KV;
import cooltu.lib4j.data.map.ListValueMap;
import cooltu.lib4j.tools.CountTool;
import cooltu.lib4j.tools.StringTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import cooltu.lib4j.ts.each.MapEach;
import com.codingtu.cooltu.constant.Constant;
import core.processor.lib.tools.NameTools;
import core.processor.modelinterface.StartModelInterface;
import core.processor.worker.model.base.SingleCoreToolsBaseModel;

public class StartModel extends SingleCoreToolsBaseModel implements StartModelInterface {
    public static final StartModel model = new StartModel();

    public StartModel() {
        super(Constant.NAME_ACT_START);
    }

    private HashMap<String, ListValueMap<Integer, KV<String, String>>> METHODS_MAP = new HashMap<>();
    private ListValueMap<String, List<KV<String, String>>> METHODS_MAP1 = new ListValueMap<>();

    public void addStart(String actFullName, String actStaticName, ListValueMap<Integer, KV<String, String>> ikv) {
        METHODS_MAP.put(actFullName, ikv);
        if (ikv != null) {
            Ts.ls(ikv, new MapEach<Integer, List<KV<String, String>>>() {
                @Override
                public boolean each(int position, Integer integer, List<KV<String, String>> kvs) {
                    METHODS_MAP1.get(actFullName).add(kvs);
                    return false;
                }
            });
        } else {
            METHODS_MAP1.get(actFullName).add(null);
        }
        Code4RequestModel.model.addActStaticName(actStaticName);
    }

    @Override
    public void setTagFor_methods(StringBuilder sb) {

        Ts.ls(METHODS_MAP1, new MapEach<String, List<List<KV<String, String>>>>() {
            @Override
            public boolean each(int position, String actFullName, List<List<KV<String, String>>> lists) {
                String actStaticName = StringTool.toStaticType(NameTools.getJavaSimpleName(actFullName));
                Ts.ls(lists, new Each<List<KV<String, String>>>() {
                    @Override
                    public boolean each(int position, List<KV<String, String>> kvs) {
                        if (CountTool.isNull(kvs)) {
                            addModel(sb, new StartMethodModel(actFullName, actStaticName));
                        } else {
                            addModel(sb, new StartMethodModel(actFullName, actStaticName, kvs));
                        }
                        return false;
                    }
                });
                return false;
            }
        });


//        Ts.ls(METHODS_MAP, new MapEach<String, ListValueMap<Integer, KV<String, String>>>() {
//            @Override
//            public boolean each(int position, String actFullName, ListValueMap<Integer, KV<String, String>> ikv) {
//                String actStaticName = NameTools.toStaticType(NameTools.getJavaSimpleName(actFullName));
//                if (ikv == null || CountTool.isNull(ikv)) {
//                    addModel(sb, new StartMethodModel(actFullName, actStaticName));
//                } else {
//                    Ts.ls(ikv, new MapEach<Integer, List<KV<String, String>>>() {
//                        @Override
//                        public boolean each(int position, Integer integer, List<KV<String, String>> kvs) {
//                            addModel(sb, new StartMethodModel(actFullName, actStaticName, kvs));
//                            return false;
//                        }
//                    });
//                }
//                return false;
//            }
//        });

    }

}
/* model_temp_start
package core.tools;

import android.app.Activity;
import android.content.Intent;

public class ActStart {

[[methods]]

}
model_temp_end */
