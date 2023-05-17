package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.codingtu.cooltu.lib4a.bean.LTRB;
import com.codingtu.cooltu.lib4a.bean.WH;
import com.codingtu.cooltu.lib4a.image.FileBitmap;
import com.codingtu.cooltu.lib4a.image.ImageTools;
import com.codingtu.cooltu.lib4a.tools.AdjustTool;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;

public class DefaultScaleImageView extends CoreScaleView {

    private Bitmap oriBitmap;
    private WH oriBitmapWH;
    private LTRB locInView;
    private LTRB showInView;
    private P scaleCenterP;
    private float maxScale = 8;
    private float minScale;
    private WH adjustWH;

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
                    locInView.toRect());

            invalidate();
        }
    }


    @Override
    protected void onMoveSingle(MotionEvent event, float dx, float dy) {
        super.onMoveSingle(event, dx, dy);
        dealMove(locInView.w(), locInView.h(), (int) (locInView.l + dx), (int) (locInView.t + dy));
    }

    @Override
    protected void onMoveMultiStart(MotionEvent event) {
        super.onMoveMultiStart(event);
        scaleCenterP = getInAreaP(getScaleCenterP(event), showInView);
    }

    @Override
    protected void onMoveMulti(MotionEvent event, float scaleAdd) {
        super.onMoveMulti(event, scaleAdd);
        if (scaleCenterP == null)
            return;

        scale *= scaleAdd;
        if (scale > maxScale) {
            scale = maxScale;
        }

        if (scale < minScale) {
            scale = minScale;
        }

        dealScale();
    }

    private void dealScale() {

        float newW = oriBitmapWH.w * scale;
        float newH = oriBitmapWH.h * scale;

        if (newW < adjustWH.w || newH < adjustWH.h) {
            newW = adjustWH.w;
            newH = adjustWH.h;
        }

        int l = (int) (scaleCenterP.x - newW * (scaleCenterP.x - locInView.l) / locInView.w());
        int t = (int) (scaleCenterP.y - newH * (scaleCenterP.y - locInView.t) / locInView.h());

        dealMove((int) newW, (int) newH, l, t);
    }

    private void dealMove(int newW, int newH, int l, int t) {
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

        LTRB ltrb = new LTRB();
        if (locInView.l >= 0) {
            ltrb.l = 0;
        } else {
            ltrb.l = (int) (-locInView.l / scale);
        }


        ltrb.r = (int) (ltrb.l + showInView.w() / scale);


        if (locInView.t >= 0) {
            ltrb.t = 0;
        } else {
            ltrb.t = (int) (-locInView.t / scale);
        }

        ltrb.b = (int) (ltrb.t + showInView.h() / scale);

        BitmapTool.drawBitmap(oriBitmap, ltrb.toRect(), drawBitmap, showInView.toRect());

        invalidate();
    }

}
