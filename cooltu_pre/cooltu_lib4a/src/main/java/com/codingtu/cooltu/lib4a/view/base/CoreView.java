package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;

public class CoreView extends View implements OnDestroy {
    public CoreView(Context context) {
        this(context, null);
    }

    public CoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        DestoryTool.onDestory(context, this);
    }

    @Override
    public void destroy() {

    }
}
