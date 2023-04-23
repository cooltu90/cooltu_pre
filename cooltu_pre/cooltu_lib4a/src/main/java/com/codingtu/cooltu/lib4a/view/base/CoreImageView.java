package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;
import com.codingtu.cooltu.lib4a.view.tools.RoundBgTool;

public class CoreImageView extends AppCompatImageView implements OnDestroy {
    private RoundBgTool roundBgTool;

    public CoreImageView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CoreImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CoreImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        DestoryTool.onDestory(context, this);

        roundBgTool = new RoundBgTool();
        roundBgTool.init(context, attrs,
                R.styleable.CoreImageView,
                R.styleable.CoreImageView_android_radius,
                R.styleable.CoreImageView_android_topLeftRadius,
                R.styleable.CoreImageView_android_topRightRadius,
                R.styleable.CoreImageView_android_bottomLeftRadius,
                R.styleable.CoreImageView_android_bottomRightRadius);
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
