package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.bean.LTRB;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;
import com.codingtu.cooltu.lib4a.view.tools.RoundBgTool;

public class CoreRelativeLayout extends RelativeLayout implements OnDestroy {

    private RoundBgTool roundBgTool;

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
        roundBgTool = new RoundBgTool();
        roundBgTool.init(context, attrs,
                R.styleable.CoreRelativeLayout,
                R.styleable.CoreRelativeLayout_android_radius,
                R.styleable.CoreRelativeLayout_android_topLeftRadius,
                R.styleable.CoreRelativeLayout_android_topRightRadius,
                R.styleable.CoreRelativeLayout_android_bottomLeftRadius,
                R.styleable.CoreRelativeLayout_android_bottomRightRadius,
                R.styleable.CoreRelativeLayout_strokeWidth,
                R.styleable.CoreRelativeLayout_strokeColor);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        roundBgTool.initBackground(this);
    }

    @Override
    public void destroy() {

    }
}
