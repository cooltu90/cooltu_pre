package com.codingtu.cooltu.lib4a.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;

import com.codingtu.cooltu.lib4a.bean.WH;
import com.codingtu.cooltu.lib4a.log.Logs;
import com.codingtu.cooltu.lib4a.tools.BitmapTool;

import java.io.File;
import java.io.IOException;

public class FileBitmap {
    private String path;

    private int size;
    private WH box;

    private Rect cut;
    private boolean notRotate;

    public static FileBitmap obtain(String path) {
        FileBitmap cutFileImage = new FileBitmap();
        cutFileImage.path = path;
        return cutFileImage;
    }

    public static FileBitmap obtain(File file) {
        return obtain(file.getAbsolutePath());
    }

    public FileBitmap size(int size) {
        this.size = size;
        return this;
    }

    public FileBitmap size(int w, int h) {
        this.box = new WH(w, h);
        return this;
    }

    public FileBitmap size(WH wh) {
        this.box = wh;
        return this;
    }

    public FileBitmap cut(Rect rect) {
        this.cut = rect;
        return this;
    }

    public FileBitmap notRotate() {
        this.notRotate = true;
        return this;
    }


    public Bitmap bitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = getSize(BitmapWH.obtain(path).wh());
        if (cut == null) {
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            if (notRotate) {
                return bitmap;
            }
            Bitmap rotate = BitmapTool.rotate(path, bitmap);
            if (bitmap != rotate) {
                BitmapTool.destoryBitmap(bitmap);
            }
            return rotate;
        } else {
            if (notRotate) {
                return cut(options, cut);
            }

            int digree = BitmapTool.getDigree(path);
            if (digree == 0) {
                return cut(options, cut);
            }

            WH wh = BitmapWH.obtain(path).notRotate().wh();
            if (digree == 90) {
                cut = new Rect(cut.top, wh.h - cut.right, cut.bottom, wh.h - cut.left);
            } else if (digree == 180) {
                cut = new Rect(wh.w - cut.right, wh.h - cut.bottom, wh.w - cut.left, wh.h - cut.top);
            } else if (digree == 270) {
                cut = new Rect(wh.w - cut.bottom, cut.left, wh.w - cut.top, cut.right);
            }
            Bitmap bitmap = cut(options, cut);
            // 旋转图片
            Bitmap rotate = BitmapTool.rotate(bitmap, digree);
            if (bitmap != rotate)
                BitmapTool.destoryBitmap(bitmap);
            return rotate;
        }
    }

    private int getSize(WH wh) {
        int size = 1;
        if (box != null) {
            if (wh.h > box.h || wh.w > box.w) {
                if (wh.w > wh.h) {
                    size = Math.round(wh.h / box.h);
                } else {
                    size = Math.round(wh.w / box.w);
                }
            }
        }
        return size;
    }

    private Bitmap cut(BitmapFactory.Options options, Rect cut) {
        try {
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(path, false);
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return decoder.decodeRegion(cut, options);
        } catch (Exception e) {

        }
        return null;
    }

}
