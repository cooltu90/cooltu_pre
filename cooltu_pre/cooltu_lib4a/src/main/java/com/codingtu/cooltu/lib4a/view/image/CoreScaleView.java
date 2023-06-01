package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.bean.LTRB;
import com.codingtu.cooltu.lib4a.bean.WH;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.tools.HandlerTool;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;
import com.codingtu.cooltu.lib4a.view.base.CoreView;

import java.util.ArrayList;
import java.util.List;

import cooltu.lib4j.data.bean.CoreBean;

public class CoreScaleView extends CoreView {

    protected Paint paint;
    protected Bitmap drawBitmap;
    protected WH viewWH;
    protected float scale;
    private Integer fingers;
    private List<P> lastPs;
    private P lastP;
    protected float maxScale;
    protected float minScale;
    protected WH adjustWH;
    protected LTRB locInView;
    protected LTRB showInView;
    protected LTRB showInBitmap;
    protected P scaleCenterP;
    protected WH oriBitmapWH;
    protected int bgColor;

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
        AttrsTools.getAttrs(context, attrs, R.styleable.CoreScaleView, new GetAttrs() {
            @Override
            public void getAttrs(Attrs attrs) {
                maxScale = attrs.getFloat(R.styleable.CoreScaleView_maxScale, 4);
                bgColor = attrs.getColor(R.styleable.CoreScaleView_android_background, Color.BLACK);
            }
        });
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
            BitmapTool.drawBitmap(canvas, drawBitmap, bgColor);
        }
    }

    private long actionDownTime;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                fingers = null;
                lastPs = null;
                lastP = null;
                actionDownTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_MOVE:
                if (System.currentTimeMillis() - actionDownTime < 100) {
                    return true;
                }
                if (fingers == null) {

                    P p = getP(event);
                    if (lastP != null) {
                        onMoveSingle(event, p.x - lastP.x, p.y - lastP.y);
                    } else {
                        onMoveSingleStart(event);
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
                long dt = System.currentTimeMillis() - actionDownTime;
                if (dt < 100) {
                    onSingleClickDeal(event);
                }
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


    private Long singleClickTime;

    private void onSingleClickDeal(MotionEvent event) {
        if (singleClickTime == null) {
            singleClickTime = System.currentTimeMillis();
            P p = getP(event);
            HandlerTool.getMainHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (singleClickTime != null) {
                        singleClickTime = null;
                        onSingleClick(p);
                    }
                }
            }, 200);
        } else {
            long l = System.currentTimeMillis() - singleClickTime;
            if (l < 200) {
                singleClickTime = null;
                onMultiClick(getP(event));
            }
        }
    }

    protected void onSingleClick(P p) {
    }

    protected void onMultiClick(P p) {
    }

    protected void onMoveSingleStart(MotionEvent event) {
    }

    protected void onMoveMultiStart(MotionEvent event) {
        scaleCenterP = getInAreaP(getScaleCenterP(event), showInView);
    }

    protected void onMoveSingle(MotionEvent event, float dx, float dy) {
        calculateMove(locInView.w(), locInView.h(), (int) (locInView.l + dx), (int) (locInView.t + dy));
        dealMove();
    }

    protected void onMoveMulti(MotionEvent event, float scaleAdd) {
        if (scaleCenterP == null)
            return;

        scale *= scaleAdd;
        if (scale > maxScale) {
            scale = maxScale;
        }

        if (scale < minScale) {
            scale = minScale;
        }

        float newW = oriBitmapWH.w * scale;
        float newH = oriBitmapWH.h * scale;

        if (newW < adjustWH.w || newH < adjustWH.h) {
            newW = adjustWH.w;
            newH = adjustWH.h;
        }

        int l = (int) (scaleCenterP.x - newW * (scaleCenterP.x - locInView.l) / locInView.w());
        int t = (int) (scaleCenterP.y - newH * (scaleCenterP.y - locInView.t) / locInView.h());

        calculateMove((int) newW, (int) newH, l, t);

        dealMove();

    }

    protected void dealMove() {

    }


    /**************************************************
     *
     * 一些计算方法
     *
     **************************************************/

    protected void calculateMove(int newW, int newH, int l, int t) {
        locInView.lw(l, newW);
        locInView.th(t, newH);

        if (locInView.w() <= viewWH.w) {
            locInView.l = (viewWH.w - locInView.w()) / 2;
            locInView.r = locInView.l + newW;
        } else if (locInView.l > 0 && locInView.r > viewWH.w) {
            locInView.l = 0;
            locInView.r = locInView.l + newW;
        } else if (locInView.l < 0 && locInView.r < viewWH.w) {
            locInView.r = viewWH.w;
            locInView.l = locInView.r - newW;
        }


        if (locInView.h() <= viewWH.h) {
            locInView.t = (viewWH.h - locInView.h()) / 2;
            locInView.b = locInView.t + newH;
        } else if (locInView.t > 0 && locInView.b > viewWH.h) {
            locInView.t = 0;
            locInView.b = locInView.t + newH;
        } else if (locInView.t < 0 && locInView.b < viewWH.h) {
            locInView.b = viewWH.h;
            locInView.t = locInView.b - newH;
        }

        showInView = locInView.copyOne();
        if (showInView.l < 0) {
            showInView.l = 0;
        }

        if (showInView.r > viewWH.w) {
            showInView.r = viewWH.w;
        }

        if (showInView.t < 0) {
            showInView.t = 0;
        }

        if (showInView.b > viewWH.h) {
            showInView.b = viewWH.h;
        }

        showInBitmap = new LTRB();
        if (locInView.l >= 0) {
            showInBitmap.l = 0;
        } else {
            showInBitmap.l = (int) (-locInView.l / scale);
        }


        showInBitmap.r = (int) (showInBitmap.l + showInView.w() / scale);


        if (locInView.t >= 0) {
            showInBitmap.t = 0;
        } else {
            showInBitmap.t = (int) (-locInView.t / scale);
        }

        showInBitmap.b = (int) (showInBitmap.t + showInView.h() / scale);
    }

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

    public static class P extends CoreBean {
        public float x;
        public float y;

        public P(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
