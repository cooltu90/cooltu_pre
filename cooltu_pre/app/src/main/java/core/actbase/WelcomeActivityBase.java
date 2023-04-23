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

public abstract class WelcomeActivityBase extends com.codingtu.cooltu.lib4a.act.CoreActivity implements View.OnClickListener, NetBackI, PermissionBack {

    protected int fromAct;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.codingtu.cooltu_pre.R.layout.activity_welcome);
        Intent data = getIntent();
        fromAct = core.tools.Pass.fromAct(data);







        onCreateComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

        }
    }


    public android.view.View.OnClickListener getOnClick() {
        return this;
    }

    @Override
    public void accept(String code, Result<ResponseBody> result, CoreSendParams params, List objs) {

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        back(requestCode, permissions, grantResults);
    }

    @Override
    public void back(int requestCode, String[] permissions, int[] grantResults) {

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

        }
    }











    private com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog toastDialog;

    protected com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog getToastDialog() {
        if (toastDialog == null)
            toastDialog = new com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog(getThis())
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog_toast)
                    .build();
        return toastDialog;
    }

    protected void toastShow(String msg) {
        com.codingtu.cooltu.lib4a.view.dialogview.ToastDialog td = getToastDialog();
        td.setContent(msg);
        td.show();
    }

    protected void toastSet(String msg) {
        if (cooltu.lib4j.tools.StringTool.isNotBlank(msg)) {
            getToastDialog().setContent(msg);
        }
    }

    protected void toastHidden(String msg, long time, com.codingtu.cooltu.lib4a.view.layerview.listener.OnHiddenFinished onHiddenFinished) {
        toastSet(msg);
        com.codingtu.cooltu.lib4a.tools.HandlerTool.getMainHandler().postDelayed(() -> getToastDialog().hidden(onHiddenFinished), time);
    }

    protected void toastHidden(String msg, com.codingtu.cooltu.lib4a.view.layerview.listener.OnHiddenFinished onHiddenFinished) {
        toastHidden(msg, 1000, onHiddenFinished);
    }

    protected void toastHidden(String msg) {
        toastHidden(msg, null);
    }


    private com.codingtu.cooltu.lib4a.view.dialogview.EditDialog ed;

    protected void showEd(String text ) {
        if (ed == null)
            ed = new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog(getThis())
                    .setTitle("xxx")
                    .setHint("xxx")
                    .setInputType(1)
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog_edit)
                    .setTextWatcher(getEdTextWatcher())
                    .setReserveOriValue(true)
                    .setYes(new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Yes() {
                        @Override
                        public boolean yes(String text, Object obj) {
                            return edYes(text);
                        }
                    })
                    .build();
        ed.setEditText(text);
        ed.setObject(null);
        ed.show();
    }

    protected com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.EdTextWatcher getEdTextWatcher() { return null;}


    protected boolean edYes(String text ) {
        return false;
    }











}
