package com.codingtu.cooltu.lib4a.view.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.codingtu.cooltu.lib4a.act.Destroys;
import com.codingtu.cooltu.lib4a.act.OnDestroy;
import com.codingtu.cooltu.lib4a.bean.LTRB;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;
import com.codingtu.cooltu.lib4a.tools.DestoryTool;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;

public class RoundBgTool implements OnDestroy {

    private int bgRadius;
    private int topLeftbgRadius;
    private int topRightbgRadius;
    private int bottomLeftbgRadius;
    private int bottomRightbgRadius;
    private Paint roundPaint;
    private Integer bgColor;
    private int strokeWidth;
    private int strokeColor;
    private Bitmap bgBitmap;
    private View view;
    private boolean isOnLayout;

    public void init(Context context, View view, AttributeSet set, int[] attrs,
                     int radiusIndex, int tlIndex, int trIndex, int blIndex, int brIndex,
                     int strokeWidthIndex, int strokeColorIndex) {
        AttrsTools.getAttrs(context, set, attrs, new GetAttrs() {
            @Override
            public void getAttrs(Attrs attrs) {
                bgRadius = attrs.getDimensionPixelSize(radiusIndex, 0);
                topLeftbgRadius = attrs.getDimensionPixelSize(tlIndex, bgRadius);
                topRightbgRadius = attrs.getDimensionPixelSize(trIndex, bgRadius);
                bottomLeftbgRadius = attrs.getDimensionPixelSize(blIndex, bgRadius);
                bottomRightbgRadius = attrs.getDimensionPixelSize(brIndex, bgRadius);
                strokeWidth = attrs.getDimensionPixelSize(strokeWidthIndex, 0);
                strokeColor = attrs.getColor(strokeColorIndex, Color.TRANSPARENT);
            }
        });
        roundPaint = DrawTool.getDefaultPaint();
        this.view = view;
        Drawable background = view.getBackground();
        if (background != null) {
            if (background instanceof ColorDrawable) {
                bgColor = ((ColorDrawable) background).getColor();
            } else if (background instanceof BitmapDrawable) {
                bgBitmap = ((BitmapDrawable) background).getBitmap();
            }
        }
    }

    private boolean isStroke() {
        return strokeWidth != 0 && strokeColor != Color.TRANSPARENT;
    }

    private Bitmap createBitmap() {
        return BitmapTool.createBitmap(view.getWidth(), view.getHeight());
    }

    private float[] getRadii() {
        return new float[]{
                topLeftbgRadius, topLeftbgRadius,
                topRightbgRadius, topRightbgRadius,
                bottomRightbgRadius, bottomRightbgRadius,
                bottomLeftbgRadius, bottomLeftbgRadius};
    }

    private float[] getStrokeRadii() {
        return new float[]{
                topLeftbgRadius - strokeWidth, topLeftbgRadius - strokeWidth,
                topRightbgRadius - strokeWidth, topRightbgRadius - strokeWidth,
                bottomRightbgRadius - strokeWidth, bottomRightbgRadius - strokeWidth,
                bottomLeftbgRadius - strokeWidth, bottomLeftbgRadius - strokeWidth};
    }

    private LTRB getLtrb() {
        return new LTRB(0, 0, view.getWidth(), view.getHeight());
    }

    private LTRB getStrokeLtrb() {
        return new LTRB(strokeWidth, strokeWidth, view.getWidth() - strokeWidth, view.getHeight() - strokeWidth);
    }

    private LTRB getBgBitmapLtrb() {
        return new LTRB(0, 0, bgBitmap.getWidth(), bgBitmap.getHeight());
    }


    private Path getRoundRectPath() {
        Path path = new Path();
        path.addRoundRect(getLtrb().toRectF(), getRadii(), Path.Direction.CW);
        return path;
    }

    private Path getStrokeRoundRectPath() {
        Path path = new Path();
        path.addRoundRect(getStrokeLtrb().toRectF(), getStrokeRadii(), Path.Direction.CW);
        return path;
    }

    public void initBackground() {
        if (bgColor != null) {
            //颜色类背景
            if (isStroke()) {
                drawStrokeWithBg();
            } else {
                drawBg();
            }
        } else if (bgBitmap != null) {
            //图片类背景
            drawBgBitmap();
        } else if (isStroke()) {
            //环并且背景透明
            drawStroke();
        }
        roundPaint.reset();
    }

    private void drawStrokeWithBg() {
        Bitmap bitmap = createBitmap();
        Canvas canvas = new Canvas(bitmap);
        //绘制环
        roundPaint.setColor(strokeColor);
        canvas.drawPath(getRoundRectPath(), roundPaint);
        //绘制内部
        roundPaint.setColor(bgColor);
        canvas.drawPath(getStrokeRoundRectPath(), roundPaint);
        view.setBackground(new BitmapDrawable(bitmap));
    }

    private void drawBg() {
        Bitmap bitmap = createBitmap();
        Canvas canvas = new Canvas(bitmap);
        roundPaint.setColor(bgColor);
        canvas.drawPath(getRoundRectPath(), roundPaint);
        view.setBackground(new BitmapDrawable(bitmap));
    }

    private void drawBgBitmap() {
        Bitmap bitmap = createBitmap();
        Canvas canvas = new Canvas(bitmap);
        canvas.drawPath(getRoundRectPath(), roundPaint);
        roundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bgBitmap, getBgBitmapLtrb().toRect(), getLtrb().toRect(), roundPaint);
        view.setBackground(new BitmapDrawable(bitmap));
    }


    private void drawStroke() {
        Bitmap bitmap = createBitmap();
        Canvas canvas = new Canvas(bitmap);
        roundPaint.setColor(strokeColor);
        canvas.drawPath(getRoundRectPath(), roundPaint);
        roundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        roundPaint.setColor(strokeColor);
        canvas.drawPath(getStrokeRoundRectPath(), roundPaint);
        view.setBackground(new BitmapDrawable(bitmap));
    }

    public void setBackgroundColor(int color) {
        bgColor = color;
        if (isOnLayout) {
            initBackground();
        }
    }

    public void setBackgroundResource(int resId) {
        bgColor = null;
        bgBitmap = BitmapFactory.decodeResource(view.getResources(), resId);
        if (isOnLayout) {
            initBackground();
        }
    }

    @Override
    public void destroy() {
        view = null;
        if (bgBitmap != null)
            bgBitmap.recycle();
        bgBitmap = null;
    }

    public void onLayout() {
        this.isOnLayout = true;
        initBackground();
    }

    public void setRadius(int radius) {
        bgRadius = radius;
        topLeftbgRadius = radius;
        topRightbgRadius = radius;
        bottomLeftbgRadius = radius;
        bottomRightbgRadius = radius;
        if (isOnLayout) {
            initBackground();
        }
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        if (isOnLayout) {
            initBackground();
        }
    }
}
