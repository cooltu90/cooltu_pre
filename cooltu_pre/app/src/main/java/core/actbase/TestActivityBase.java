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
    public com.codingtu.cooltu.lib4a.view.dialogview.Dialog dialog;
    public com.codingtu.cooltu.lib4a.view.dialogview.Dialog dialog1;
    public com.codingtu.cooltu.lib4a.view.dialogview.EditDialog editDialog;


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









    protected void showDialog(com.codingtu.cooltu_pre.bean.User user) {
        if (dialog == null) {
            dialog = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getThis())
                    .setTitle("dialog")
                    .setContent("xxxx")
                    .setLeftBtText("删除1")
                    .setRighBtText("更新")
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialogLeft(obj != null ? (com.codingtu.cooltu_pre.bean.User) obj : null);
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialogRight(obj != null ? (com.codingtu.cooltu_pre.bean.User) obj : null);
                        }
                    })
                    .build();
        }
        dialog.setObject(user);
        dialog.show();
    }
    protected void showDialog(String content ,com.codingtu.cooltu_pre.bean.User user) {
        if (dialog == null) {
            dialog = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getThis())
                    .setTitle("dialog")
                    .setContent(content)
                    .setLeftBtText("删除1")
                    .setRighBtText("更新")
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialogLeft(obj != null ? (com.codingtu.cooltu_pre.bean.User) obj : null);
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialogRight(obj != null ? (com.codingtu.cooltu_pre.bean.User) obj : null);
                        }
                    })
                    .build();
        }else{
            dialog.updateContent(content);
        }
        dialog.setObject(user);
        dialog.show();
    }

    protected void dialogLeft(com.codingtu.cooltu_pre.bean.User user) {}
    protected void dialogRight(com.codingtu.cooltu_pre.bean.User user) {}


    protected void showDialog1( ) {
        if (dialog1 == null) {
            dialog1 = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getThis())
                    .setTitle("dialog")
                    .setContent("xxxx")
                    .setLeftBtText("取消")
                    .setRighBtText("确定")
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialog1Left();
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialog1Right();
                        }
                    })
                    .build();
        }
        dialog1.setObject(null);
        dialog1.show();
    }
    protected void showDialog1(String content ) {
        if (dialog1 == null) {
            dialog1 = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getThis())
                    .setTitle("dialog")
                    .setContent(content)
                    .setLeftBtText("取消")
                    .setRighBtText("确定")
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialog1Left();
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialog1Right();
                        }
                    })
                    .build();
        }else{
            dialog1.updateContent(content);
        }
        dialog1.setObject(null);
        dialog1.show();
    }

    protected void dialog1Left( ) {}
    protected void dialog1Right( ) {}


    protected void showDialog3( ) {
        if (dialog3 == null) {
            dialog3 = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getThis())
                    .setTitle("dialog")
                    .setContent("xxxx")
                    .setLeftBtText("取消")
                    .setRighBtText("确定")
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialog3Left();
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialog3Right();
                        }
                    })
                    .build();
        }
        dialog3.setObject(null);
        dialog3.show();
    }
    protected void showDialog3(String content ) {
        if (dialog3 == null) {
            dialog3 = new com.codingtu.cooltu.lib4a.view.dialogview.Dialog(getThis())
                    .setTitle("dialog")
                    .setContent(content)
                    .setLeftBtText("取消")
                    .setRighBtText("确定")
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog)
                    .setOnBtClick(new com.codingtu.cooltu.lib4a.view.dialogview.Dialog.OnBtClick() {
                        @Override
                        public void onLeftClick(Object obj) {
                            dialog3Left();
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            dialog3Right();
                        }
                    })
                    .build();
        }else{
            dialog3.updateContent(content);
        }
        dialog3.setObject(null);
        dialog3.show();
    }

    protected void dialog3Left( ) {}
    protected void dialog3Right( ) {}






    private com.codingtu.cooltu.lib4a.view.dialogview.NoticeDialog noticeDialog;

    protected void noticeShow(String msg) {
        if (noticeDialog == null)
            noticeDialog = new com.codingtu.cooltu.lib4a.view.dialogview.NoticeDialog(getThis())
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog_notice)
                    .build();
        noticeDialog.setContent(msg);
        noticeDialog.show();
    }


    protected void showEditDialog(String text ) {
        if (editDialog == null)
            editDialog = new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Builder(getThis())
                    .setTitle("提示")
                    .setHint("请输入")
                    .setInputType(1)
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog_edit)
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


    protected void showEd2(String text ) {
        if (ed2 == null)
            ed2 = new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Builder(getThis())
                    .setTitle("提示")
                    .setHint("请输入")
                    .setInputType(1)
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog_edit)
                    .setYes(new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Yes() {
                        @Override
                        public boolean yes(String text, Object obj) {
                            return ed2Yes(text);
                        }
                    })
                    .build();
        ed2.setEditText(text);
        ed2.setObject(null);
        ed2.show();
    }

    

    protected boolean ed2Yes(String text ) {
        return false;
    }


    protected void showEd1(String text ) {
        if (ed1 == null)
            ed1 = new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Builder(getThis())
                    .setTitle("提示")
                    .setHint("请输入")
                    .setInputType(1)
                    .setLayout(com.codingtu.cooltu.lib4a.R.layout.default_dialog_edit)
                    .setYes(new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog.Yes() {
                        @Override
                        public boolean yes(String text, Object obj) {
                            return ed1Yes(text);
                        }
                    })
                    .build();
        ed1.setEditText(text);
        ed1.setObject(null);
        ed1.show();
    }

    

    protected boolean ed1Yes(String text ) {
        return false;
    }











}
