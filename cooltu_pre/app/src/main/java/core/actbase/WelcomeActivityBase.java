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

    public android.widget.TextView tv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.codingtu.cooltu_pre.R.layout.activity_welcome);
        Intent data = getIntent();
        fromAct = core.tools.Pass.fromAct(data);

        tv = findViewById(com.codingtu.cooltu_pre.R.id.tv);






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













    private com.codingtu.cooltu.lib4a.view.dialogview.EditDialog ed;

    protected void showEd(String text ) {
        if (ed == null)
            ed = new com.codingtu.cooltu.lib4a.view.dialogview.EditDialog(getThis())
                    .setTitle("xxx")
                    .setHint("xxx")
                    .setInputType(1)
                    .setLayout(com.codingtu.cooltu_pre.R.layout.dialog_et)
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

    protected android.text.TextWatcher getEdTextWatcher() { return null;}


    protected boolean edYes(String text ) {
        return false;
    }











}
