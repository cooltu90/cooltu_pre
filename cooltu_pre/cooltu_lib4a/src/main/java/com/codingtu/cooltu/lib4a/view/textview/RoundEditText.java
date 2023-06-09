package com.codingtu.cooltu.lib4a.view.textview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;
import com.codingtu.cooltu.lib4a.view.base.CoreEditText;
import com.codingtu.cooltu.lib4a.view.tools.RoundBgTool;

public class RoundEditText extends CoreEditText {


    private RoundBgTool roundBgTool;

    public RoundEditText(@NonNull Context context) {
        super(context);
    }

    public RoundEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        roundBgTool = new RoundBgTool();
        DestoryTool.onDestory(context, roundBgTool);
        roundBgTool.init(context, this, attrs,
                R.styleable.RoundEditText,
                R.styleable.RoundEditText_android_radius,
                R.styleable.RoundEditText_android_topLeftRadius,
                R.styleable.RoundEditText_android_topRightRadius,
                R.styleable.RoundEditText_android_bottomLeftRadius,
                R.styleable.RoundEditText_android_bottomRightRadius,
                R.styleable.RoundEditText_strokeWidth,
                R.styleable.RoundEditText_strokeColor);
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
