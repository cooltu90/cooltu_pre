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

public abstract class FormActivityBase extends com.codingtu.cooltu.lib4a.act.CoreActivity implements View.OnClickListener, NetBackI, PermissionBack {

    protected int fromAct;

    public android.widget.LinearLayout rgLl;
    public android.widget.SeekBar seekBar;
    public android.widget.EditText et;
    public android.widget.TextView bt;
    public com.codingtu.cooltu_pre.bean.TestForm testForm;
    public boolean initFormBean;
    public BindHandler handler;
    public com.codingtu.cooltu.lib4a.view.combine.RadioGroup rgRg;
    public com.codingtu.cooltu_pre.form.DefaultOnSetItem defaultOnSetItem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.codingtu.cooltu_pre.R.layout.activity_form);
        Intent data = getIntent();
        fromAct = core.tools.Pass.fromAct(data);

        rgLl = findViewById(com.codingtu.cooltu_pre.R.id.rgLl);
        seekBar = findViewById(com.codingtu.cooltu_pre.R.id.seekBar);
        et = findViewById(com.codingtu.cooltu_pre.R.id.et);
        bt = findViewById(com.codingtu.cooltu_pre.R.id.bt);

        bt.setOnClickListener(this);





        defaultOnSetItem = new com.codingtu.cooltu_pre.form.DefaultOnSetItem();
        rgRg = com.codingtu.cooltu.lib4a.view.combine.RadioGroup.obtain(this).setBts(rgLl).setOnSetItem(defaultOnSetItem);
        rgLl.setTag(com.codingtu.cooltu.lib4a.R.id.tag_0, rgRg);
        initFormView();
        if (testForm == null) {
            testForm = new com.codingtu.cooltu_pre.bean.TestForm();
            initFormBean = true;
        }
        handler = new BindHandler(testForm);
        et.addTextChangedListener(new com.codingtu.cooltu.lib4a.view.textview.HandlerTextWatcher(handler,0, 0));
        rgRg.addOnSelectChange(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSelectChange(handler,2, 0));
        seekBar.setOnSeekBarChangeListener(new com.codingtu.cooltu.lib4a.view.combine.HandlerOnSeekBarChangeListener(handler,3, 0));
        if (!initFormBean) {
            com.codingtu.cooltu.lib4a.tools.ViewTool.setEditTextAndSelection(et, testForm.name);
            if (com.codingtu.cooltu.lib4j.tools.StringTool.isNotBlank(testForm.rg)) {
                rgRg.setSelected(new com.codingtu.cooltu.lib4a.form.DefaultRadioGroupToString("梨1", "苹果1", "芒果1").toView(testForm.rg));
            }
            seekBar.setProgress(new com.codingtu.cooltu_pre.form.SeekBarParse().toView(testForm.time));
        }

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
                if (!checkTestForm()) {
                    return;
                }
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



    public static class BindHandler extends android.os.Handler {
        private com.codingtu.cooltu_pre.bean.TestForm testForm;

        public BindHandler(com.codingtu.cooltu_pre.bean.TestForm testForm) {
            this.testForm = testForm;
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                switch (msg.arg1) {
                    case 0:
                        testForm.name = (java.lang.String) msg.obj;
                        break;
                }
            }
            if (msg.what == 2) {
                switch (msg.arg1) {
                    case 0:
                        testForm.rg = new com.codingtu.cooltu.lib4a.form.DefaultRadioGroupToString("梨1", "苹果1", "芒果1").toBean(msg.obj);
                        break;
                }
            }
            if (msg.what == 3) {
                switch (msg.arg1) {
                    case 0:
                        testForm.time = new com.codingtu.cooltu_pre.form.SeekBarParse().toBean(msg.obj);
                        break;
                }
            }


        }


        private com.codingtu.cooltu.lib4j.data.map.ListValueMap<Integer, com.codingtu.cooltu.lib4a.form.FormLink> links;

        private com.codingtu.cooltu.lib4j.data.map.ListValueMap<Integer, com.codingtu.cooltu.lib4a.form.FormLink> getLinks() {
            if (links == null) {
                links = new com.codingtu.cooltu.lib4j.data.map.ListValueMap<>();
            }
            return links;
        }

        public void addLink(int index, com.codingtu.cooltu.lib4a.form.FormLink link) {
            getLinks().get(index).add(link);
        }

        private void link(int id) {
            com.codingtu.cooltu.lib4j.ts.Ts.ls(getLinks().get(id), new com.codingtu.cooltu.lib4j.ts.impl.BaseTs.EachTs<com.codingtu.cooltu.lib4a.form.FormLink>() {
                @Override
                public boolean each(int position, com.codingtu.cooltu.lib4a.form.FormLink formLink) {
                    formLink.link();
                    return false;
                }
            });
        }
    }


    protected boolean checkTestForm() {
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(testForm.rg)) {
            toast("请选择");
            return false;
        }
        if (!new com.codingtu.cooltu_pre.form.SeekBarsCheck().check(testForm, testForm.time)) {
            toast("请选择时间");
            return false;
        }
        if (com.codingtu.cooltu.lib4j.tools.StringTool.isBlank(testForm.name)) {
            toast("请输入名字");
            return false;
        }
        return true;
    }


    protected void initFormView() {}















}
