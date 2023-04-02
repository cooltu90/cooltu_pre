package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;

public class CoreRelativeLayout extends RelativeLayout implements OnDestroy {
    public CoreRelativeLayout(Context context) {
        this(context, null);
    }

    public CoreRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoreRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public CoreRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);

    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        DestoryTool.onDestory(context, this);
    }

    @Override
    public void destroy() {

    }
}
