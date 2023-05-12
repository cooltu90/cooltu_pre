package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.codingtu.cooltu.lib4a.CoreApp;
import com.codingtu.cooltu.lib4a.image.ImageTools;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;
import com.codingtu.cooltu.lib4a.tools.DrawTool;
import com.codingtu.cooltu.lib4a.view.base.CoreView;

public class ScaleImageViewNew extends CoreView {

    private Bitmap oriBitmap;
    private boolean isInit;
    private Paint paint;

    public ScaleImageViewNew(Context context) {
        super(context);
    }

    public ScaleImageViewNew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaleImageViewNew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        paint = DrawTool.getDefaultPaint();
    }

    public void setImage(String image) {
        ImageTools.getImage(image, new ImageTools.ImageBitmapGetter() {
            @Override
            public void callBack(Bitmap bitmap) {
                oriBitmap = bitmap;
                readSetImage();
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        isInit = true;
        readSetImage();
    }

    private void readSetImage() {
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit || oriBitmap == null) {
            return;
        }

    }
}
