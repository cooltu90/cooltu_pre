package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.codingtu.cooltu.lib4a.bean.LTRB;
import com.codingtu.cooltu.lib4a.bean.WH;
import com.codingtu.cooltu.lib4a.image.FileBitmap;
import com.codingtu.cooltu.lib4a.image.ImageTools;
import com.codingtu.cooltu.lib4a.tools.AdjustTool;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;

import cooltu.lib4j.data.bean.Scale;

public class DefaultScaleImageView extends CoreScaleView {

    private Bitmap oriBitmap;

    public DefaultScaleImageView(Context context) {
        super(context);
    }

    public DefaultScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
    }

    @Override
    protected void onViewCompleted() {
        super.onViewCompleted();
        initBitmap();
    }

    public void setFile(String image) {
        initBitmap(FileBitmap.obtain(image).bitmap());
    }

    public void setImageUrl(String imageUrl) {
        ImageTools.getImage(imageUrl, new ImageTools.ImageBitmapGetter() {
            @Override
            public void callBack(Bitmap bitmap) {
                initBitmap(bitmap);
            }
        });
    }


    private void initBitmap(Bitmap bitmap) {
        oriBitmap = bitmap;
        oriBitmapWH = BitmapTool.getBitmapWH(oriBitmap);
        initBitmap();
    }

    private void initBitmap() {
        if (viewWH != null && oriBitmap != null) {
            adjustWH = AdjustTool.inBox(viewWH, oriBitmapWH);
            scale = (float) adjustWH.w / oriBitmapWH.w;
            minScale = scale;

            locInView = new LTRB();
            locInView.lw((viewWH.w - adjustWH.w) / 2, adjustWH.w);
            locInView.th((viewWH.h - adjustWH.h) / 2, adjustWH.h);

            showInView = locInView.copyOne();

            if (drawBitmap == null) {
                drawBitmap = BitmapTool.createBitmap(viewWH);
            }

            BitmapTool.drawBitmap(oriBitmap,
                    drawBitmap,
                    locInView.toRect(), Color.BLACK);

            invalidate();
        }
    }

    @Override
    protected void dealMove() {
        super.dealMove();
        BitmapTool.drawBitmap(oriBitmap, showInBitmap.toRect(), drawBitmap, showInView.toRect(), Color.BLACK);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //10,10,1000,1000

        Scale scale = new Scale(this.scale);
        double s1 = scale.getDoubleSize(500);
        double s2 = scale.getDoubleSize(1000);

        int l = (int) (locInView.l + s1);
        int r = (int) (locInView.l + s2);

        int t = (int) (locInView.t + s1);
        int b = (int) (locInView.t + s2);

        paint.setColor(Color.RED);
        canvas.drawRect(l, t, r, b, paint);


        paint.reset();
    }
}
