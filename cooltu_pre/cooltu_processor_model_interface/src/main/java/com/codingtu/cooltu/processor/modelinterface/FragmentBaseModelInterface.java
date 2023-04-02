package com.codingtu.cooltu.processor.modelinterface;

import java.util.ArrayList;
import java.util.List;

public interface FragmentBaseModelInterface {

    public void setTagFor_name(StringBuilder sb);

    public void setTagFor_base(StringBuilder sb);

    public void setTagFor_longClickListener(StringBuilder sb);

    public void setTagFor_field(StringBuilder sb);

    public void setTagFor_addBus(StringBuilder sb);

    public void setTagFor_InflateTool(StringBuilder sb);

    public void setTagFor_rPkg(StringBuilder sb);

    public void setTagFor_layoutName(StringBuilder sb);

    public void setTagFor_findView(StringBuilder sb);

    public void setTagFor_setClick(StringBuilder sb);

    public void setTagFor_setLongClick(StringBuilder sb);

    public void setTagFor_onCreates(StringBuilder sb);

    public void setTagFor_binds(StringBuilder sb);

    public void setTagFor_resources(StringBuilder sb);

    public void setTagFor_onDestroys(StringBuilder sb);

    public void setTagFor_onclicks(StringBuilder sb);

    public void setTagFor_clickMethods(StringBuilder sb);

    public void setTagFor_netMethos(StringBuilder sb);

    public void setTagFor_netBackMethods(StringBuilder sb);

    public void setTagFor_bindHandler(StringBuilder sb);

    public void setTagFor_bindCheck(StringBuilder sb);

    public void setTagFor_others(StringBuilder sb);

    public void setTagFor_dialogs(StringBuilder sb);

    public void setTagFor_toastDialog(StringBuilder sb);

    public void setTagFor_editDialog(StringBuilder sb);

    public void setTagFor_onLongClick(StringBuilder sb);

    public void setTagFor_onLongClickMethods(StringBuilder sb);

    public void setTagFor_results(StringBuilder sb);

    public void setTagFor_resultMethos(StringBuilder sb);

    public void setTagFor_busBackMethods(StringBuilder sb);

    default List<String> getTempLinesArray() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("package core.fragmentbase;");
        lines.add("");
        lines.add("import android.os.Bundle;");
        lines.add("import android.view.LayoutInflater;");
        lines.add("import android.view.View;");
        lines.add("import android.view.ViewGroup;");
        lines.add("import android.widget.TextView;");
        lines.add("");
        lines.add("import androidx.annotation.NonNull;");
        lines.add("import androidx.annotation.Nullable;");
        lines.add("");
        lines.add("import com.codingtu.cooltu.lib4a.act.OnActBack;");
        lines.add("import com.codingtu.cooltu.lib4a.net.bean.CoreSendParams;");
        lines.add("import com.codingtu.cooltu.lib4a.net.netback.NetBackI;");
        lines.add("import okhttp3.ResponseBody;");
        lines.add("import retrofit2.adapter.rxjava2.Result;");
        lines.add("");
        lines.add("import java.util.HashMap;");
        lines.add("import java.util.List;");
        lines.add("import java.util.Map;");
        lines.add("");
        lines.add("public class [[name]] extends [[base]] implements View.OnClickListener, NetBackI, OnActBack [[longClickListener]]{");
        lines.add("[[field]]");
        lines.add("");
        lines.add("    @Nullable");
        lines.add("    @Override");
        lines.add("    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {");
        lines.add("[[addBus]]");
        lines.add("        View view = [[InflateTool]].inflate(inflater, [[rPkg]].R.layout.[[layoutName]], container);");
        lines.add("[[findView]]");
        lines.add("[[setClick]]");
        lines.add("[[setLongClick]]");
        lines.add("[[onCreates]]");
        lines.add("[[binds]]");
        lines.add("[[resources]]");
        lines.add("        ((com.codingtu.cooltu.lib4a.act.CoreActivity) getActivity()).addOnActBack(this);");
        lines.add("        onCreateView(view);");
        lines.add("        return view;");
        lines.add("    }");
        lines.add("");
        lines.add("    public void onCreateView(View view) {}");
        lines.add("");
        lines.add("    @Override");
        lines.add("    public void onDestroy() {");
        lines.add("        super.onDestroy();");
        lines.add("[[onDestroys]]");
        lines.add("    }");
        lines.add("");
        lines.add("");
        lines.add("    @Override");
        lines.add("    public void onClick(View view) {");
        lines.add("");
        lines.add("        switch (view.getId()) {");
        lines.add("[[onclicks]]");
        lines.add("        }");
        lines.add("    }");
        lines.add("[[clickMethods]]");
        lines.add("");
        lines.add("    public android.view.View.OnClickListener getOnClick() {");
        lines.add("        return this;");
        lines.add("    }");
        lines.add("");
        lines.add("    @Override");
        lines.add("    public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {");
        lines.add("[[netMethos]]");
        lines.add("    }");
        lines.add("");
        lines.add("[[netBackMethods]]");
        lines.add("");
        lines.add("[[bindHandler]]");
        lines.add("");
        lines.add("[[bindCheck]]");
        lines.add("");
        lines.add("[[others]]");
        lines.add("");
        lines.add("[[dialogs]]");
        lines.add("");
        lines.add("[[toastDialog]]");
        lines.add("");
        lines.add("[[editDialog]]");
        lines.add("");
        lines.add("[[onLongClick]]");
        lines.add("");
        lines.add("[[onLongClickMethods]]");
        lines.add("");
        lines.add("    @Override");
        lines.add("    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {");
        lines.add("        super.onActivityResult(requestCode, resultCode, data);");
        lines.add("        if (resultCode == android.app.Activity.RESULT_OK) {");
        lines.add("[[results]]");
        lines.add("        }");
        lines.add("    }");
        lines.add("");
        lines.add("[[resultMethos]]");
        lines.add("");
        lines.add("[[busBackMethods]]");
        lines.add("");
        lines.add("}");
        return lines;
    }
}
