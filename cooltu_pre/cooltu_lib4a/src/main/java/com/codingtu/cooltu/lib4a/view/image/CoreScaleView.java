package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.view.base.CoreView;

public class CoreScaleView extends CoreView {

    private Paint paint;
    private boolean isScale;

    public CoreScaleView(Context context) {
        super(context);
    }

    public CoreScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoreScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        paint = DrawTool.getDefaultPaint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                isScale = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isScale) {
                    moveMulti(event);
                } else {
                    moveSingle(event);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                isScale = true;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isScale = false;
                break;
        }
        return true;
    }

    private void moveSingle(MotionEvent event) {
        Logs.i("moveSingle");
    }

    private void moveMulti(MotionEvent event) {
        Logs.i("moveMulti");
    }


    private P getP(MotionEvent event) {
        return new P(event.getX(), event.getY());
    }

    private P getP(MotionEvent event, int index) {
        return new P(event.getX(index), event.getY(index));
    }

    public static class P {
        public float x;
        public float y;

        public P(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
