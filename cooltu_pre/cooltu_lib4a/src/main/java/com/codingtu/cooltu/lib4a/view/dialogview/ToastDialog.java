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
import com.codingtu.cooltu.lib4a.view.layerview.listener.OnHiddenFinished;

public class ToastDialog implements OnDestroy {

    private Activity act;
    private RelativeLayerView rlv;
    private int layout;
    private View inflate;
    private View contentTv;

    public ToastDialog(Activity act) {
        this.act = act;
        if (act instanceof Destroys) {
            ((Destroys) act).add(this);
        }
    }

    @Override
    public void destroy() {
        contentTv = null;
        inflate = null;
        rlv = null;
        act = null;
    }

    public ToastDialog setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public ToastDialog build() {
        return build(false, false);
    }

    public ToastDialog build(boolean isHiddenWhenBackClick, boolean isHiddenWhenShadowClick) {
        rlv = new RelativeLayerView(act);
        rlv.setHiddenWhenBackClick(isHiddenWhenBackClick);
        rlv.setHiddenWhenShadowClick(isHiddenWhenShadowClick);
        ViewTool.addToAct(act, rlv);
        ViewTool.gone(rlv);
        inflate = InflateTool.inflate(act, layout);
        rlv.addView(inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        contentTv = inflate.findViewById(R.id.dialogContentTv);
        ViewTool.inRelativeCenter(inflate);
        return this;
    }

    public void setHiddenWhenBackClick(boolean isHiddenWhenBackClick) {
        rlv.setHiddenWhenBackClick(isHiddenWhenBackClick);
    }

    public void setHiddenWhenShadowClick(boolean isHiddenWhenShadowClick) {
        rlv.setHiddenWhenShadowClick(isHiddenWhenShadowClick);
    }


    public ToastDialog setContent(String text) {
        ViewTool.setText(contentTv, text);
        return this;
    }

    public String getContent() {
        return ((TextView) contentTv).getText().toString();
    }

    public void show() {
        rlv.show();
    }

    public void hidden(OnHiddenFinished onHiddenFinished) {
        rlv.hidden(onHiddenFinished);
    }

    public void hidden() {
        if(ViewTool.isVisible(rlv)){
            rlv.hidden();
        }
    }
}
