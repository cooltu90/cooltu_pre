package com.codingtu.cooltu.lib4a.view.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.bean.LTRB;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;

public class CoreLinearLayout extends LinearLayout implements OnDestroy {
    private int bgRadius;
    private int topLeftbgRadius;
    private int topRightbgRadius;
    private int bottomLeftbgRadius;
    private int bottomRightbgRadius;
    private int roundBgColor;
    private Paint roundPaint;

    public CoreLinearLayout(Context context) {
        this(context, null);
    }

    public CoreLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoreLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public CoreLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);

    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        DestoryTool.onDestory(context, this);

        AttrsTools.getAttrs(context, attrs, R.styleable.CoreView, new GetAttrs() {
            @Override
            public void getAttrs(Attrs attrs) {
                bgRadius = attrs.getDimensionPixelSize(R.styleable.CoreView_bg_radius, 0);
                topLeftbgRadius = attrs.getDimensionPixelSize(R.styleable.CoreView_bg_top_left_radius, bgRadius);
                topRightbgRadius = attrs.getDimensionPixelSize(R.styleable.CoreView_bg_top_right_radius, bgRadius);
                bottomLeftbgRadius = attrs.getDimensionPixelSize(R.styleable.CoreView_bg_bottom_left_radius, bgRadius);
                bottomRightbgRadius = attrs.getDimensionPixelSize(R.styleable.CoreView_bg_bottom_right_radius, bgRadius);
                roundBgColor = attrs.getColor(R.styleable.CoreView_roundBgColor, Color.TRANSPARENT);
            }
        });
        if (roundBgColor != Color.TRANSPARENT) {
            roundPaint = DrawTool.getDefaultPaint();
            roundPaint.setColor(roundBgColor);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0 || roundPaint == null) {
            return;
        }
        LTRB ltrb = new LTRB(0, 0, width, height);
        Path path = new Path();
        path.addRoundRect(ltrb.toRectF(), new float[]{
                topLeftbgRadius, topLeftbgRadius,
                topRightbgRadius, topRightbgRadius,
                bottomRightbgRadius, bottomRightbgRadius,
                bottomLeftbgRadius, bottomLeftbgRadius}, Path.Direction.CW);
        canvas.drawPath(path, roundPaint);
    }
}
