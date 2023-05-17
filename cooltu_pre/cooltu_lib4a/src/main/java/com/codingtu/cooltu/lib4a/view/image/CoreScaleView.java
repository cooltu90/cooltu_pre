package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.codingtu.cooltu.constant.TouchType;
import com.codingtu.cooltu.lib4a.bean.LTRB;
import com.codingtu.cooltu.lib4a.bean.WH;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.view.base.CoreView;

import java.util.ArrayList;
import java.util.List;

public class CoreScaleView extends CoreView {

    private Paint paint;
    protected Bitmap drawBitmap;
    protected WH viewWH;
    protected float scale;
    private Integer fingers;
    private List<P> lastPs;
    private P lastP;

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
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (viewWH == null) {
            viewWH = new WH(getWidth(), getHeight());
            onViewCompleted();
        } else if (viewWH.w != getWidth() || viewWH.h != getHeight()) {
            viewWH = new WH(getWidth(), getHeight());
            onViewCompleted();
        }
    }

    protected void onViewCompleted() {

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawBitmap != null && !drawBitmap.isRecycled()) {
            canvas.drawColor(Color.WHITE);
            BitmapTool.drawBitmap(canvas, drawBitmap);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                fingers = null;
                lastPs = null;
                lastP = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (fingers == null) {

                    P p = getP(event);
                    if (lastP != null) {
                        onMoveSingle(event, p.x - lastP.x, p.y - lastP.y);
                    }
                    lastP = p;
                } else if (fingers == 2 && event.getPointerCount() == 2) {
                    List<P> currentPs = getPs(event);
                    if (lastPs != null) {
                        //处理
                        float scaleAdd = calculateScale(currentPs, lastPs);
                        onMoveMulti(event, scaleAdd);
                    } else {
                        onMoveMultiStart(event);
                    }
                    lastPs = currentPs;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (fingers == null) {
                    fingers = event.getPointerCount();
                } else if (fingers < event.getPointerCount()) {
                    fingers = event.getPointerCount();
                }
                lastPs = null;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
        }
        return true;
    }

    protected void onMoveMultiStart(MotionEvent event) {

    }

    protected void onMoveSingle(MotionEvent event, float dx, float dy) {
    }

    protected void onMoveMulti(MotionEvent event, float scaleAdd) {
    }


    /**************************************************
     *
     * 一些计算方法
     *
     **************************************************/

    protected float calculateScale(List<P> currentPs, List<P> lastPs) {
        int size = currentPs.size();

        double d1 = 0;
        double d2 = 0;
        int num = 0;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                d1 += getDistance(currentPs.get(i), currentPs.get(j));
                d2 += getDistance(lastPs.get(i), lastPs.get(j));
                num++;
            }
        }
        d1 = d1 / (double) num;
        d2 = d2 / (double) num;

        return (float) (d1 / d2);
    }

    protected double getDistance(P p1, P p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }


    protected P getP(MotionEvent event) {
        return new P(event.getX(), event.getY());
    }

    protected P getP(MotionEvent event, int index) {
        return new P(event.getX(index), event.getY(index));
    }

    protected List<P> getPs(MotionEvent event) {
        ArrayList<P> ps = new ArrayList<>();
        for (int i = 0; i < event.getPointerCount(); i++) {
            ps.add(getP(event, i));
        }
        return ps;
    }

    protected P getInAreaP(P p, LTRB ltrb) {
        return isInArea(p, ltrb) ? p : null;
    }

    protected boolean isInArea(P p, LTRB ltrb) {
        return p == null ? false : (ltrb.l <= p.x && p.x <= ltrb.r && ltrb.t <= p.y && p.y <= ltrb.b);
    }

    protected P getScaleCenterP(MotionEvent event) {
        int pointerCount = event.getPointerCount();
        float x = 0;
        float y = 0;
        for (int i = 0; i < event.getPointerCount(); i++) {
            x += event.getX(i);
            y += event.getY(i);
        }
        return new P(x / (float) pointerCount, y / (float) pointerCount);
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
