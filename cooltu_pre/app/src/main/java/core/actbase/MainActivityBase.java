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

public abstract class MainActivityBase extends com.codingtu.cooltu.lib4a.act.CoreActivity implements View.OnClickListener, NetBackI, PermissionBack {

    protected int fromAct;

    public android.widget.Button bt1;
    public android.widget.Button bt2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.codingtu.cooltu_pre.R.layout.activity_main);
        Intent data = getIntent();
        fromAct = core.tools.Pass.fromAct(data);

        bt1 = findViewById(com.codingtu.cooltu_pre.R.id.bt1);
        bt2 = findViewById(com.codingtu.cooltu_pre.R.id.bt2);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);





        onCreateComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case com.codingtu.cooltu_pre.R.id.bt1:
                bt1Click(

                );
                break;
            case com.codingtu.cooltu_pre.R.id.bt2:
                bt2Click(

                );
                break;

        }
    }
    protected void bt1Click() {}
    protected void bt2Click() {}


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
        if (requestCode == core.tools.Permissions.CODE_CHECK) {
            check(com.codingtu.cooltu.lib4a.permission.PermissionTool.allow(grantResults));
        }

    }

    public void check(boolean isAllow) {}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

        }
    }
























}
