package com.codingtu.cooltu.lib4a.view.layerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.codingtu.cooltu.lib4a.view.layerview.listener.LayerListener;

public class RelativeLayerView extends LayerView {
    private View dialogView;
    private ScaleAnimation showScaleAnim;
    private ScaleAnimation hiddenScaleAnim;
    private boolean isShowOnLayoutComplete;

    public RelativeLayerView(Context context) {
        super(context);
    }

    public RelativeLayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeLayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RelativeLayerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        int w = getMeasuredWidth();
        int dialogW = dialogView.getMeasuredWidth();
        int left = (w - dialogW) / 2;
        dialogView.layout(left, dialogView.getTop(), left + dialogW, dialogView.getBottom());
        if (isShowOnLayoutComplete) {
            isShowOnLayoutComplete = false;
            if (!stopAnimation) {
                dialogView.startAnimation(showScaleAnim);
            }
        }

    }

    @Override
    public void show() {
        super.show();
        if (dialogView != null) {
            if (!stopAnimation) {
                dialogView.startAnimation(showScaleAnim);
            }
        } else {
            isShowOnLayoutComplete = true;
        }
    }

    @Override
    public void hidden(LayerListener layerListener) {
        super.hidden(layerListener);
        if (!stopAnimation) {
            dialogView.startAnimation(hiddenScaleAnim);
        }
    }

    public View getDialogView() {
        return dialogView;
    }

    public void stopAnimation() {
        this.stopAnimation = true;
    }

}
