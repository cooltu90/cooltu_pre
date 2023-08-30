package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.data.bean.KV;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;
import com.codingtu.cooltu.lib4j.ts.impl.MapTs;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;
import com.codingtu.cooltu.processor.modelinterface.ActBaseModelInterface;
import com.codingtu.cooltu.processor.worker.ModelType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public class ActBaseModel extends BaseParentModel implements ActBaseModelInterface {


    private List<ExecutableElement> permissionElements = new ArrayList<>();

    private Map<String, String> startFieldMap = new HashMap<>();
    private ListValueMap<Integer, KV<String, String>> startParams;

    public ActBaseModel(JavaInfo info) {
        super(info, true);
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    public ModelType getModelType() {
        return ModelType.actBase;
    }

    public void addPermission(ExecutableElement element) {
        permissionElements.add(element);
    }

    public void addStartParams(ListValueMap<Integer, KV<String, String>> ikv) {
        this.startParams = ikv;
    }

    @Override
    protected String getFindViewByIdParent(String viParent) {
        return StringTool.isNotBlank(viParent) ? (viParent + ".") : "";
    }

    /**************************************************
     *
     *
     *
     **************************************************/


    @Override
    public void setTagFor_startDatas(StringBuilder startDatasSb) {
    }

    @Override
    public void setTagFor_field(StringBuilder fieldSb) {
        super.setTagFor_field(fieldSb);
        if (!CountTool.isNull(startParams)) {
            StringBuilder startDatasSb = getTag("startDatas");
            Ts.ts(startParams).ls(new MapTs.MapEach<Integer, List<KV<String, String>>>() {
                @Override
                public boolean each(Integer integer, List<KV<String, String>> kvs) {
                    Ts.ls(kvs, new BaseTs.EachTs<KV<String, String>>() {
                        @Override
                        public boolean each(int position, KV<String, String> kv) {
                            if (!startFieldMap.containsKey(kv.v)) {
                                startFieldMap.put(kv.v, kv.v);
                                addFieldSb(fieldSb, kv.k, kv.v);
                                addLnTag(startDatasSb, "        [name] = core.tools.Pass.[name](data);", kv.v, kv.v);
                            }
                            return false;
                        }
                    });
                    return false;
                }
            });
        }
    }

    @Override
    public void setTagFor_permissionMethods(StringBuilder sb) {
        Ts.ls(permissionElements, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                List<? extends VariableElement> ves = element.getParameters();
                String allow = "";
                if (CountTool.count(ves) == 1) {
                    String type = ves.get(0).asType().toString();
                    if (ClassTool.isType(type, boolean.class, Boolean.class)) {
                        allow = type + " isAllow";
                    }
                }
                addLnTag(sb,
                        "    public void [name]([allow]) {}",
                        ElementTools.simpleName(element),
                        allow);
                return false;
            }
        });
    }

    @Override
    public void setTagFor_permissions(StringBuilder sb) {
        Ts.ls(permissionElements, new BaseTs.EachTs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement element) {
                addModel(sb, new ActBasePermissionBackModel(position != 0, element));
                return false;
            }
        });
    }
}

/* model_temp_start
package core.actbase;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import android.content.Intent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;
import com.codingtu.cooltu.lib4a.net.netback.NetBackI;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

import com.codingtu.cooltu.lib4a.permission.PermissionBack;

public abstract class [[name]] extends [[base]] implements View.OnClickListener, NetBackI, PermissionBack [[longClickListener]]{

    protected int fromAct;

[[field]]

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
[[addBus]]
        setContentView([[rPkg]].R.layout.[[layoutName]]);
        Intent data = getIntent();
        fromAct = core.tools.Pass.fromAct(data);
[[startDatas]]
[[findView]]
[[setClick]]
[[setLongClick]]
[[onCreates]]
[[resources]]
[[binds]]
        onCreateComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
[[onDestroys]]
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
[[onclicks]]
        }
    }
[[clickMethods]]

    public android.view.View.OnClickListener getOnClick() {
        return this;
    }

    @Override
    public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {
[[netMethos]]
    }

[[netBackMethods]]


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        back(requestCode, permissions, grantResults);
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {
[[permissions]]
    }

[[permissionMethods]]

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
[[results]]
        }
    }

[[resultMethos]]

[[bindHandler]]

[[bindCheck]]

[[others]]

[[dialogs]]

[[toastDialog]]

[[noticeDialog]]

[[editDialog]]

[[onLongClick]]

[[onLongClickMethods]]

[[busBackMethods]]


}
model_temp_end */
