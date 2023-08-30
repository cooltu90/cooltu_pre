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

public abstract class TestActivityBase extends com.codingtu.cooltu_pre.ui.BaseTestActivity implements View.OnClickListener, NetBackI, PermissionBack {

    protected int fromAct;

    public android.widget.TextView tv1;
    public android.widget.TextView tv2;
    public android.widget.EditText et;
    public int color11;
    public int color22;
    public int dp11;
    public int d1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.codingtu.cooltu_pre.R.layout.activity_test);
        Intent data = getIntent();
        fromAct = core.tools.Pass.fromAct(data);
        id = core.tools.Pass.id(data);
        name = core.tools.Pass.name(data);
        money = core.tools.Pass.money(data);
        age = core.tools.Pass.age(data);

        tv = findViewById(com.codingtu.cooltu_pre.R.id.tv);
        tv1 = findViewById(com.codingtu.cooltu_pre.R.id.tv1);
        tv2 = findViewById(com.codingtu.cooltu_pre.R.id.tv2);
        et = findViewById(com.codingtu.cooltu_pre.R.id.et);

        tv.setOnClickListener(this);
        tv1.setOnClickListener(this);



        dp11 = com.codingtu.cooltu.lib4a.tools.MobileTool.dpToPx(2.6f);
        dp1 = com.codingtu.cooltu.lib4a.tools.MobileTool.dpToPx(2.5f);
        d1 = com.codingtu.cooltu.lib4a.tools.ResourceTool.getDimen(com.codingtu.cooltu_pre.R.dimen.d1);
        dp2 = com.codingtu.cooltu.lib4a.tools.ResourceTool.getDimen(com.codingtu.cooltu_pre.R.dimen.d1);
        color22 = com.codingtu.cooltu.lib4a.tools.ResourceTool.getColor(com.codingtu.cooltu_pre.R.color.purple_200);
        color2 = com.codingtu.cooltu.lib4a.tools.ResourceTool.getColor(com.codingtu.cooltu_pre.R.color.black);
        color11 = android.graphics.Color.parseColor("#ffffff");
        color1 = android.graphics.Color.parseColor("#ffffff");


        onCreateComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case com.codingtu.cooltu_pre.R.id.tv:
                tvClick(

                );
                break;
            case com.codingtu.cooltu_pre.R.id.tv1:
                tv1Click(

                );
                break;

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
            if (requestCode == core.tools.Code4Request.STEP_ONE_ACTIVITY) {
                stepOneActivityBack(core.tools.Pass.age(data));
            }

        }
    }









    private com.codingtu.cooltu.lib4a.view.dialogview.Dialog dialog;

    protected void showDialog( ) {
        if (dialog == null) {
            dialog = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getThis())
                    .setTitle("dialog")
                    .setContent("xxxx")
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog)
                    .setYes(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.Yes() {
                        @Override
                        public void yes(Object obj) {
                            dialogYes();
                        }
                    })
                    .build();
        }
        dialog.setObject(null);
        dialog.show();
    }
    protected void showDialog(String content ) {
        if (dialog == null) {
            dialog = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getThis())
                    .setTitle("dialog")
                    .setContent(content)
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog)
                    .setYes(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.Yes() {
                        @Override
                        public void yes(Object obj) {
                            dialogYes();
                        }
                    })
                    .build();
        }else{
            dialog.updateContent(content);
        }
        dialog.setObject(null);
        dialog.show();
    }

    protected void dialogYes( ) {}




    private com.codingtu.cooltu.lib4a.view.dialogview.EditDialog editDialog;

    protected void showEditDialog(String text ) {
        if (editDialog == null)
            editDialog = new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog(getThis())
                    .setTitle("提示")
                    .setHint("请输入")
                    .setInputType(1)
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog_edit)
                    .setReserveOriValue(true)
                    .stopAnimation()
                    .setYes(new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Yes() {
                        @Override
                        public boolean yes(String text, Object obj) {
                            return editDialogYes(text);
                        }
                    })
                    .build();
        editDialog.setEditText(text);
        editDialog.setObject(null);
        editDialog.show();
    }

    

    protected boolean editDialogYes(String text ) {
        return false;
    }











}
