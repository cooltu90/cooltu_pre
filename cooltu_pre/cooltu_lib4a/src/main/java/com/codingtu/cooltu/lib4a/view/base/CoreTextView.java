package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.util.AttributeSet;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;
import com.codingtu.cooltu.lib4a.view.tools.RoundBgTool;

public class CoreTextView extends androidx.appcompat.widget.AppCompatTextView implements OnDestroy {

    public CoreTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CoreTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
