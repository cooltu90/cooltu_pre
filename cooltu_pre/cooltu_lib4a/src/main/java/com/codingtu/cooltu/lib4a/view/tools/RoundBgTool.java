package com.codingtu.cooltu.lib4a.view.tools;

import android.content.Context;
import android.graphics.Bitmap;
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

import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;

public class RoundBgTool {

    private int bgRadius;
    private int topLeftbgRadius;
    private int topRightbgRadius;
    private int bottomLeftbgRadius;
    private int bottomRightbgRadius;
    private Paint roundPaint;
    private Integer bgColor;
    private int strokeWidth;
    private int strokeColor;

    public void init(Context context, AttributeSet set, int[] attrs,
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
    }

    public void initBackground(View view) {
        Drawable background = view.getBackground();
        if (background != null) {
            Bitmap bitmap = BitmapTool.createBitmap(view.getWidth(), view.getHeight());
            Canvas canvas = new Canvas(bitmap);
            if (bgColor != null) {
                if (strokeWidth != 0) {
                    drawStrokePath(view, canvas, bgColor);
                } else {
                    roundPaint.setColor(bgColor);
                    drawPath(view, canvas);
                }
            } else if (background instanceof ColorDrawable) {
                bgColor = ((ColorDrawable) background).getColor();
                if (strokeWidth != 0) {
                    drawStrokePath(view, canvas, bgColor);
                } else {
                    roundPaint.setColor(bgColor);
                    drawPath(view, canvas);
                }
            } else if (background instanceof BitmapDrawable) {
                Bitmap bitmap1 = ((BitmapDrawable) background).getBitmap();
                drawPath(view, canvas);
                roundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(bitmap1, new Rect(0, 0, bitmap1.getWidth(), bitmap1.getHeight())
                        , new Rect(0, 0, view.getWidth(), view.getHeight()), roundPaint);
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
            view.setBackground(bitmapDrawable);
        }
    }

    private void drawPath(View view, Canvas canvas) {
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, view.getWidth(), view.getHeight()), new float[]{
                topLeftbgRadius, topLeftbgRadius,
                topRightbgRadius, topRightbgRadius,
                bottomRightbgRadius, bottomRightbgRadius,
                bottomLeftbgRadius, bottomLeftbgRadius}, Path.Direction.CW);
        canvas.drawPath(path, roundPaint);
    }

    private void drawStrokePath(View view, Canvas canvas, int bgColor) {
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, view.getWidth(), view.getHeight()), new float[]{
                topLeftbgRadius, topLeftbgRadius,
                topRightbgRadius, topRightbgRadius,
                bottomRightbgRadius, bottomRightbgRadius,
                bottomLeftbgRadius, bottomLeftbgRadius}, Path.Direction.CW);
        roundPaint.setColor(strokeColor);
        canvas.drawPath(path, roundPaint);
        path = new Path();
        path.addRoundRect(new RectF(strokeWidth, strokeWidth, view.getWidth() - strokeWidth, view.getHeight() - strokeWidth), new float[]{
                topLeftbgRadius - strokeWidth, topLeftbgRadius - strokeWidth,
                topRightbgRadius - strokeWidth, topRightbgRadius - strokeWidth,
                bottomRightbgRadius - strokeWidth, bottomRightbgRadius - strokeWidth,
                bottomLeftbgRadius - strokeWidth, bottomLeftbgRadius - strokeWidth}, Path.Direction.CW);
        roundPaint.setColor(bgColor);
        canvas.drawPath(path, roundPaint);
    }
}
