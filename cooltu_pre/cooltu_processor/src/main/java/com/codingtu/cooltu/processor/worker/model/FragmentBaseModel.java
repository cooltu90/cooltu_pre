package com.codingtu.cooltu.processor.worker.model;

import com.codingtu.cooltu.lib4j.data.bean.JavaInfo;
import com.codingtu.cooltu.lib4j.tools.StringTool;

import com.codingtu.cooltu.constant.FullName;

import java.util.List;

import com.codingtu.cooltu.processor.modelinterface.FragmentBaseModelInterface;
import com.codingtu.cooltu.processor.worker.ModelType;

public class FragmentBaseModel extends BaseParentModel implements FragmentBaseModelInterface {

    public FragmentBaseModel(JavaInfo info) {
        super(info, false);
    }

    @Override
    public ModelType getModelType() {
        return ModelType.fragmentBase;
    }

    @Override
    public List<String> getTempLines() {
        return getTempLinesArray();
    }

    @Override
    protected String getFindViewByIdParent(String viParent) {
        return StringTool.isNotBlank(viParent) ? (viParent + ".") : "view.";
    }

    /***************************************
     *
     *
     *
     ***************************************/

    @Override
    public void setTagFor_InflateTool(StringBuilder sb) {
        addTag("InflateTool", FullName.INFLATE_TOOL);
    }

}
/* model_temp_start
package core.fragmentbase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.act.OnActBack;
import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;
import com.codingtu.cooltu.lib4a.net.netback.NetBackI;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class [[name]] extends [[base]] implements View.OnClickListener, NetBackI, OnActBack [[longClickListener]]{
[[field]]

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
[[addBus]]
        View view = [[InflateTool]].inflate(inflater, [[rPkg]].R.layout.[[layoutName]], container);
[[findView]]
[[setClick]]
[[setLongClick]]
[[onCreates]]
[[binds]]
[[resources]]
        ((com.codingtu.cooltu.lib4a.act.CoreActivity) getActivity()).addOnActBack(this);
        onCreateView(view);
        return view;
    }

    public void onCreateView(View view) {}

    @Override
    public void onDestroy() {
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

[[bindHandler]]

[[bindCheck]]

[[others]]

[[dialogs]]

[[toastDialog]]

[[noticeDialog]]

[[editDialog]]

[[onLongClick]]

[[onLongClickMethods]]

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {
[[results]]
        }
    }

[[resultMethos]]

[[busBackMethods]]

}
model_temp_end */
