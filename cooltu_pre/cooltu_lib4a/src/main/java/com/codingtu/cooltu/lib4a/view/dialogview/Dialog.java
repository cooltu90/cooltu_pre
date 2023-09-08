package com.codingtu.cooltu.lib4a.view.dialogview;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.act.Destroys;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.view.layerview.RelativeLayerView;
import com.codingtu.cooltu.lib4a.tools.InflateTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;

public class Dialog implements View.OnClickListener, OnDestroy {

    private Activity act;
    private String title;
    private String content;
    private RelativeLayerView rlv;
    private View rightBt;
    private View leftBt;
    private View inflate;
    private Object obj;
    private Yes yes;
    private OnBtClick onBtClick;
    private int layout;
    private View contentTv;
    private String leftBtText;
    private String rightBtText;


    public Dialog(Activity act) {
        this.act = act;
        if (act instanceof Destroys) {
            ((Destroys) act).add(this);
        }
    }

    public Dialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public Dialog setContent(String content) {
        this.content = content;
        return this;
    }

    public Dialog setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public Dialog setYes(Yes yes) {
        this.yes = yes;
        return this;
    }

    public Dialog setOnBtClick(OnBtClick onBtClick) {
        this.onBtClick = onBtClick;
        return this;
    }

    public Dialog setLeftBtText(String text) {
        this.leftBtText = text;
        return this;
    }

    public Dialog setRighBtText(String text) {
        this.rightBtText = text;
        return this;
    }

    public Dialog build() {
        rlv = new RelativeLayerView(act);
        ViewTool.addToAct(act, rlv);
        inflate = InflateTool.inflate(act, layout);
        rlv.addView(inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        ViewTool.gone(rlv);

        TextView titleTv = inflate.findViewById(R.id.dialogTitleTv);
        ViewTool.setText(titleTv, title);
        contentTv = inflate.findViewById(R.id.dialogContentTv);
        ViewTool.setText(contentTv, content);

        leftBt = inflate.findViewById(R.id.dialogLeftBt);
        rightBt = inflate.findViewById(R.id.dialogRightBt);
        ViewTool.setText(leftBt, leftBtText);
        ViewTool.setText(rightBt, rightBtText);
        leftBt.setOnClickListener(this);
        rightBt.setOnClickListener(this);
        ViewTool.inRelativeCenter(inflate);
        return this;
    }

    public void updateContent(String content) {
        ViewTool.setText(contentTv, content);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.dialogRightBt) {
            clickYesBt(v);
            obj = null;
            return;
        }

        if (vId == R.id.dialogLeftBt) {
            clickNoBt(v);
            obj = null;
            return;
        }
    }

    private void clickYesBt(View v) {
        rlv.hidden();
        if (yes != null) {
            yes.yes(obj);
        }
    }

    private void clickNoBt(View v) {
        rlv.hidden();
    }

    public Dialog setObject(Object obj) {
        this.obj = obj;
        return this;
    }

    @Override
    public void destroy() {
        if (rightBt != null) {
            rightBt.setOnClickListener(null);
        }
        if (leftBt != null) {
            leftBt.setOnClickListener(null);
        }
        rightBt = null;
        leftBt = null;
        rlv = null;
        inflate = null;
        yes = null;
        onBtClick = null;
        obj = null;
        contentTv = null;
        act = null;
    }

    public void show() {
        rlv.show();
    }


    public static interface Yes {
        public void yes(Object obj);
    }

    public static interface OnBtClick {
        public void onLeftClick(Object obj);

        public void onRightClick(Object obj);
    }
}
