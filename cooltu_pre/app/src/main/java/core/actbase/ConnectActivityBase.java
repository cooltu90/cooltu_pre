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

public abstract class ConnectActivityBase extends com.codingtu.cooltu.lib4a.act.CoreActivity implements View.OnClickListener, NetBackI, PermissionBack {

    protected int fromAct;

    public android.widget.Button bt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.codingtu.cooltu_pre.R.layout.activity_connect);
        Intent data = getIntent();
        fromAct = core.tools.Pass.fromAct(data);

        bt = findViewById(com.codingtu.cooltu_pre.R.id.bt);

        bt.setOnClickListener(this);





        onCreateComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case com.codingtu.cooltu_pre.R.id.bt:
                btClick(

                );
                break;

        }
    }
    protected void btClick() {}


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
























}
