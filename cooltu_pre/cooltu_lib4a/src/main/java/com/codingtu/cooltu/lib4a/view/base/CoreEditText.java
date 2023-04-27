package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;
import com.codingtu.cooltu.lib4a.view.tools.RoundBgTool;

public class CoreEditText extends androidx.appcompat.widget.AppCompatEditText implements OnDestroy {
    private RoundBgTool roundBgTool;

    public CoreEditText(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public CoreEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CoreEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        DestoryTool.onDestory(context, this);
        roundBgTool = new RoundBgTool();
        DestoryTool.onDestory(context, roundBgTool);
        roundBgTool.init(context, this, attrs,
                R.styleable.CoreEditText,
                R.styleable.CoreEditText_android_radius,
                R.styleable.CoreEditText_android_topLeftRadius,
                R.styleable.CoreEditText_android_topRightRadius,
                R.styleable.CoreEditText_android_bottomLeftRadius,
                R.styleable.CoreEditText_android_bottomRightRadius,
                R.styleable.CoreEditText_strokeWidth,
                R.styleable.CoreEditText_strokeColor);
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

    @Override
    public void destroy() {
        roundBgTool = null;
    }
}
