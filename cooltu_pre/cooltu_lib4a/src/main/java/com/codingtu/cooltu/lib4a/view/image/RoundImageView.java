package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.bean.LTRB;
import com.codingtu.cooltu.lib4a.bean.WH;

import com.codingtu.cooltu.lib4a.tools.AdjustTool;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;
import com.codingtu.cooltu.lib4a.tools.RectTool;
import com.codingtu.cooltu.lib4a.view.base.CoreImageView;

public class RoundImageView extends CoreImageView {

    private String bgColor;
    private int cornerRadius; // 统一设置圆角半径，优先级高于单独设置每个角的半径
    private int cornerTopLeftRadius; // 左上角圆角半径
    private int cornerTopRightRadius; // 右上角圆角半径
    private int cornerBottomLeftRadius; // 左下角圆角半径
    private int cornerBottomRightRadius; // 右下角圆角半径

    private Bitmap drawBitmap;

    private Xfermode xfermode;

    private Paint paint;

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, 0, 0);
        for (int i = 0; i < ta.getIndexCount(); i++) {
            int attr = ta.getIndex(i);
            if (attr == R.styleable.RoundImageView_corner_radius) {
                cornerRadius = ta.getDimensionPixelSize(attr, cornerRadius);
            } else if (attr == R.styleable.RoundImageView_corner_top_left_radius) {
                cornerTopLeftRadius = ta.getDimensionPixelSize(attr, cornerTopLeftRadius);
            } else if (attr == R.styleable.RoundImageView_corner_top_right_radius) {
                cornerTopRightRadius = ta.getDimensionPixelSize(attr, cornerTopRightRadius);
            } else if (attr == R.styleable.RoundImageView_corner_bottom_left_radius) {
                cornerBottomLeftRadius = ta.getDimensionPixelSize(attr, cornerBottomLeftRadius);
            } else if (attr == R.styleable.RoundImageView_corner_bottom_right_radius) {
                cornerBottomRightRadius = ta.getDimensionPixelSize(attr, cornerBottomRightRadius);
            } else if (attr == R.styleable.RoundImageView_bg_color) {
                bgColor = ta.getString(attr);
            }

        }
        ta.recycle();

        Drawable background = getBackground();

        if (background != null) {
            if (background instanceof ColorDrawable) {
                int color = ((ColorDrawable) background).getColor();

                Bitmap bitmap = BitmapTool.createBitmap(100, 100);
                Canvas canvas = new Canvas(bitmap);
                canvas.drawColor(color);
                drawBitmap = bitmap;
                setBackground(null);
            } else if (background instanceof BitmapDrawable) {
                drawBitmap = ((BitmapDrawable) background).getBitmap();
                setBackground(null);
            }
        }

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        if (bgColor != null) {
            paint.setColor(Color.parseColor(bgColor));
        }

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawBitmap != null) {

            ScaleType scaleType = getScaleType();
            if (scaleType.equals(ScaleType.CENTER_INSIDE)) {
                int w = getWidth();
                int h = getHeight();
                int bw = drawBitmap.getWidth();
                int bh = drawBitmap.getHeight();

                WH wh = AdjustTool.inBox(w, h, bw, bh);

                RectF rectF = new RectF(0, 0, w, h);
                int saveCount = canvas.saveLayer(rectF, paint, Canvas.ALL_SAVE_FLAG);
                canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

                paint.setXfermode(xfermode);

                LTRB dstLtrb = new LTRB();
                dstLtrb.lw((w - wh.w) / 2, wh.w);
                dstLtrb.th((h - wh.h) / 2, wh.h);

                canvas.drawBitmap(drawBitmap, new LTRB(0, 0, bw, bh).toRect(), dstLtrb.toRect(), paint);

                //清除混合模式
                paint.setXfermode(null);
                //还原画布
                canvas.restoreToCount(saveCount);
            } else {
                int w = getWidth();
                int h = getHeight();
                int bw = drawBitmap.getWidth();
                int bh = drawBitmap.getHeight();

                WH wh = AdjustTool.inBox(bw, bh, w, h);
                LTRB ltrb = new LTRB();
                ltrb.lw((bw - wh.w) / 2, wh.w);
                ltrb.th((bh - wh.h) / 2, wh.h);

                Rect dst = RectTool.newRect(w, h);

                RectF rectF = new RectF(dst);
                int saveCount = canvas.saveLayer(rectF, paint, Canvas.ALL_SAVE_FLAG);
                canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

                paint.setXfermode(xfermode);
                canvas.drawBitmap(drawBitmap, ltrb.toRect(), dst, paint);

                //清除混合模式
                paint.setXfermode(null);
                //还原画布
                canvas.restoreToCount(saveCount);
            }

        }

    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        if (drawable == null)
            return;
//        if (drawBitmap != null)
//            drawBitmap.recycle();
        drawBitmap = ((BitmapDrawable) drawable).getBitmap();
        invalidate();
    }


    public void setBgColor(String colorStr) {
        bgColor = colorStr;
        int color = Color.parseColor(colorStr);
        paint.setColor(color);
        Bitmap bitmap = BitmapTool.createBitmap(100, 100);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(color);
        drawBitmap = bitmap;
        invalidate();
    }
}
