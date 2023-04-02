package com.codingtu.cooltu.lib4a.view.layerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

import com.codingtu.cooltu.lib4a.tools.ViewTool;
import com.codingtu.cooltu.lib4a.view.layerview.listener.LayerListener;

public class LeftListLayerView extends LayerView {
    private View dialogView;
    private boolean isComplete;
    private AnimationSet showAnimationSet;
    private AnimationSet hiddenAnimationSet;

    public LeftListLayerView(Context context) {
        super(context);
    }

    public LeftListLayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeftListLayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LeftListLayerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);

        showAnimationSet = new AnimationSet(true);
        showAnimationSet.addAnimation(new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0));
        showAnimationSet.addAnimation(new AlphaAnimation(0f, 1f));
        showAnimationSet.setDuration(defaultDuration);


        hiddenAnimationSet = new AnimationSet(true);
        hiddenAnimationSet.addAnimation(new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0));
        hiddenAnimationSet.addAnimation(new AlphaAnimation(1f, 0f));
        hiddenAnimationSet.setDuration(defaultDuration);

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
//        int w = getMeasuredWidth();
//        int dialogW = dialogView.getMeasuredWidth();
//        int left = w - dialogW;
//        dialogView.layout(0, dialogView.getTop(), w, dialogView.getBottom());
    }

    @Override
    public void show() {
        super.show();
        if (isComplete) {
            showReal();
        } else {
            ViewTool.completeView(this, new ViewTool.ViewComplete() {
                @Override
                public void viewComplete() {
                    isComplete = true;
                    showReal();
                }
            });
        }
    }

    private void showReal() {
        dialogView.startAnimation(showAnimationSet);
    }

    @Override
    public void hidden(LayerListener layerListener) {
        super.hidden(layerListener);
        dialogView.startAnimation(hiddenAnimationSet);
    }
}
