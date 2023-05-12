package com.codingtu.cooltu.lib4a.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;

import com.codingtu.cooltu.lib4a.bean.WH;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;

import java.io.File;
import java.io.IOException;

public class CutFileImage {

    private String path;
    private int size;
    private Rect rect;
    private boolean notRotate;

    public static CutFileImage obtain(String path) {
        CutFileImage cutFileImage = new CutFileImage();
        cutFileImage.path = path;
        return cutFileImage;
    }

    public static CutFileImage obtain(File file) {
        return obtain(file.getAbsolutePath());
    }

    public CutFileImage size(int size) {
        this.size = size;
        return this;
    }

    public CutFileImage notRotate() {
        notRotate = true;
        return this;
    }

    public Bitmap cut(Rect rect) throws IOException {
        this.rect = rect;
        if (notRotate) {
            return getBitmap();
        }

        int digree = BitmapTool.getDigree(path);
        if (digree == 0) {
            return getBitmap();
        }

        WH wh = BitmapWH.obtain(path).notRotate().wh();
        if (digree == 90) {
            this.rect = new Rect(rect.top, wh.h - rect.right, rect.bottom, wh.h - rect.left);
        } else if (digree == 180) {
            this.rect = new Rect(wh.w - rect.right, wh.h - rect.bottom, wh.w - rect.left, wh.h - rect.top);

        } else if (digree == 270) {
            this.rect = new Rect(wh.w - rect.bottom, rect.left, wh.w - rect.top, rect.right);
        }
        Bitmap bitmap = getBitmap();
        // 旋转图片
        Bitmap rotate = BitmapTool.rotate(bitmap, digree);
        if (bitmap != rotate)
            BitmapTool.destoryBitmap(bitmap);
        return rotate;

    }

    public Bitmap getBitmap() throws IOException {
        BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(path, false);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = size <= 1 ? 1 : size;
        return decoder.decodeRegion(rect, options);
    }
}
