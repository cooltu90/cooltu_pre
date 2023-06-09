package com.codingtu.cooltu.lib4a.view.textview;

import android.content.Context;
import android.util.AttributeSet;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;
import com.codingtu.cooltu.lib4a.view.base.CoreTextView;
import com.codingtu.cooltu.lib4a.view.tools.RoundBgTool;

public class RoundTextView extends CoreTextView {
    protected RoundBgTool roundBgTool;

    public RoundTextView(Context context) {
        super(context);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        roundBgTool = new RoundBgTool();
        DestoryTool.onDestory(context, roundBgTool);
        roundBgTool.init(context, this, attrs,
                R.styleable.RoundTextView,
                R.styleable.RoundTextView_android_radius,
                R.styleable.RoundTextView_android_topLeftRadius,
                R.styleable.RoundTextView_android_topRightRadius,
                R.styleable.RoundTextView_android_bottomLeftRadius,
                R.styleable.RoundTextView_android_bottomRightRadius,
                R.styleable.RoundTextView_strokeWidth,
                R.styleable.RoundTextView_strokeColor);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        roundBgTool.onLayout();
    }

    @Override
    public void setBackgroundColor(int color) {
        roundBgTool.setBackgroundColor(color);
    }

    @Override
    public void setBackgroundResource(int resId) {
        roundBgTool.setBackgroundResource(resId);
    }

    public void setStrokeColor(int strokeColor) {
        roundBgTool.setStrokeColor(strokeColor);
    }

    @Override
    public void destroy() {
        roundBgTool = null;
    }


}
