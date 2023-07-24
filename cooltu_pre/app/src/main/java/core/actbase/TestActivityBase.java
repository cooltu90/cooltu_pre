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






















}
