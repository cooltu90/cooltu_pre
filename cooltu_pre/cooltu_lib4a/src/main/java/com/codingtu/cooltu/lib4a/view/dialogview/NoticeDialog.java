package com.codingtu.cooltu.lib4a.view.dialogview;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.act.Destroys;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.InflateTool;
import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.layerview.RelativeLayerView;
import com.codingtu.cooltu.lib4a.view.layerview.listener.OnHiddenFinished;

public class NoticeDialog implements OnDestroy, View.OnClickListener {

    private Activity act;
    private RelativeLayerView rlv;
    private int layout;
    private View inflate;
    private View contentTv;

    public NoticeDialog(Activity act) {
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

    public NoticeDialog setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public NoticeDialog build() {
        rlv = new RelativeLayerView(act);
        rlv.setHiddenWhenBackClick(false);
        rlv.setHiddenWhenShadowClick(false);
        ViewTool.addToAct(act, rlv);
        ViewTool.gone(rlv);
        inflate = InflateTool.inflate(act, layout);
        rlv.addView(inflate, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);
        contentTv = inflate.findViewById(R.id.dialogContentTv);
        inflate.findViewById(R.id.noticeDialogYesBt).setOnClickListener(this);

        ViewTool.inRelativeCenter(inflate);
        return this;
    }

    public NoticeDialog setContent(String text) {
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
        rlv.hidden();
    }

    @Override
    public void onClick(View v) {
        hidden();
    }

}
