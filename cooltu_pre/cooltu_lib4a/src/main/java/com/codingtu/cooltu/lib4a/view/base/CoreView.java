package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.bean.LTRB;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;
import com.codingtu.cooltu.lib4a.view.tools.RoundBgTool;

public class CoreView extends View implements OnDestroy {

    private RoundBgTool roundBgTool;

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

        roundBgTool = new RoundBgTool();
        roundBgTool.init(context, attrs,
                R.styleable.CoreView,
                R.styleable.CoreView_bg_radius,
                R.styleable.CoreView_bg_top_left_radius,
                R.styleable.CoreView_bg_top_right_radius,
                R.styleable.CoreView_bg_bottom_left_radius,
                R.styleable.CoreView_bg_bottom_right_radius
        );

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
