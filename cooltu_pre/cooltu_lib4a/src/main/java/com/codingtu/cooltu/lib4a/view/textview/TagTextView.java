package com.codingtu.cooltu.lib4a.view.textview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;

public class TagTextView extends RoundTextView{
    public TagTextView(Context context) {
        super(context);
    }

    public TagTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
    }

    public void setRadius(int radius) {
        roundBgTool.setRadius(radius);
    }
}
