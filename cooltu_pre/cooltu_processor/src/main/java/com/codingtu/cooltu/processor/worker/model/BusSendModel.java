package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.constant.Constant;

import java.util.ArrayList;
import java.util.List;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.data.bean.KV;
import cooltu.lib4j.tools.ClassTool;
import cooltu.lib4j.ts.Ts;
import cooltu.lib4j.ts.each.Each;
import com.codingtu.cooltu.processor.lib.tools.NameTools;
import com.codingtu.cooltu.processor.modelinterface.BusSendModelInterface;
import com.codingtu.cooltu.processor.worker.model.base.SingleCoreToolsBaseModel;

public class BusSendModel extends SingleCoreToolsBaseModel implements BusSendModelInterface {

    public static final BusSendModel model = new BusSendModel();

    private List<KV<String, String>> kvs = new ArrayList<>();

    public BusSendModel() {
        super(Constant.NAME_BUS_SEND);
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    public void add(KV<String, String> kv) {
        kvs.add(kv);
    }

    @Override
    public void setTagFor_methods(StringBuilder sb) {
        Ts.ls(kvs, new Each<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                JavaInfo busInfo = NameTools.getBusInfo(kv.v);

                String realParams = "null";
                String params = "";
                if (!ClassTool.isVoid(kv.k)) {
                    realParams = "data";
                    params = kv.k + " data";
                }

                addLnTag(sb, "    public static void [bluetooth]([params]) {", kv.v, params);
                addLnTag(sb, "        BusStation.send([BluetoothBus].TASK, [readParams]);", busInfo.fullName, realParams);
                addLnTag(sb, "    }");
                return false;
            }
        });

    }

    @Override
    public void setTagFor_name(StringBuilder sb) {
        addTag(sb, info.name);
    }
}
/* model_temp_start
package core.tools;

import com.codingtu.cooltu.lib4a.bus.BusStation;

public class [[name]] {
[[methods]]
}

model_temp_end */
