package com.codingtu.cooltu.lib4a.view.layerview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.codingtu.cooltu.lib4a.view.layerview.listener.LayerListener;

public class CenterLayerView extends LayerView {
    private View dialogView;
    private ScaleAnimation showScaleAnim;
    private ScaleAnimation hiddenScaleAnim;
    private boolean isShowOnLayoutComplete;

    public CenterLayerView(Context context) {
        super(context);
    }

    public CenterLayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterLayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CenterLayerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        showScaleAnim = new ScaleAnimation(0.0f, 1f, 0.0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        showScaleAnim.setDuration(defaultDuration);

        hiddenScaleAnim = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        hiddenScaleAnim.setDuration(defaultDuration);
    }

    @Override
    public void addView(View child) {
        if (getChildCount() > 1) {
            throw new IllegalStateException("DialogLayerView不能添加子View");
        }
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        if (getChildCount() > 1) {
            throw new IllegalStateException("DialogLayerView只能有一个子View");
        }
        super.addView(child, index);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (getChildCount() > 1) {
            throw new IllegalStateException("DialogLayerView只能有一个子View");
        }
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() > 1) {
            throw new IllegalStateException("DialogLayerView只能有一个子View");
        }
        super.addView(child, index, params);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (dialogView == null)
            dialogView = getChildAt(1);
        int h = getMeasuredHeight();
        int w = getMeasuredWidth();
        int dialogW = dialogView.getMeasuredWidth();
        int dialogH = dialogView.getMeasuredHeight();
        int top = (h - dialogH) / 2;
        int left = (w - dialogW) / 2;
        dialogView.layout(left, top, left + dialogW, top + dialogH);
        if (isShowOnLayoutComplete) {
            isShowOnLayoutComplete = false;
            dialogView.startAnimation(showScaleAnim);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if (dialogView == null)
//            dialogView = getChildAt(1);
//        int dp296 = dpToPx(296);
//        int w = getMeasuredWidth() * 2 / 3;
//
//        ViewGroup.LayoutParams lp = dialogView.getLayoutParams();
//        lp.width =  w > dp296 ? dp296 : w;
//        dialogView.setLayoutParams(lp);
    }

    public int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getContext().getResources().getDisplayMetrics());
    }

    @Override
    public void show() {
        super.show();
        if (dialogView != null) {
            dialogView.startAnimation(showScaleAnim);
        } else {
            isShowOnLayoutComplete = true;
        }
    }

    @Override
    public void hidden(LayerListener layerListener) {
        super.hidden(layerListener);
        dialogView.startAnimation(hiddenScaleAnim);
    }
}
