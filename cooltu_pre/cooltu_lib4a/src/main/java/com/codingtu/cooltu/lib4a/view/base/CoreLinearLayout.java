package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;
import com.codingtu.cooltu.lib4a.view.tools.RoundBgTool;

public class CoreLinearLayout extends LinearLayout implements OnDestroy {
    private RoundBgTool roundBgTool;

    public CoreLinearLayout(Context context) {
        this(context, null);
    }

    public CoreLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoreLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public CoreLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);

    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        DestoryTool.onDestory(context, this);

        roundBgTool = new RoundBgTool();
        roundBgTool.init(context, attrs,
                R.styleable.CoreLinearLayout,
                R.styleable.CoreLinearLayout_android_radius,
                R.styleable.CoreLinearLayout_android_topLeftRadius,
                R.styleable.CoreLinearLayout_android_topRightRadius,
                R.styleable.CoreLinearLayout_android_bottomLeftRadius,
                R.styleable.CoreLinearLayout_android_bottomRightRadius,
                R.styleable.CoreLinearLayout_strokeWidth,
                R.styleable.CoreLinearLayout_strokeColor);
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
