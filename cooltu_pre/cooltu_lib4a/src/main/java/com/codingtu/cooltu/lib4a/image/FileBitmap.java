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

    private int digree;

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
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;
        WH oriWH = new WH(options.outWidth, options.outHeight);

        if (cut == null) {
            if (notRotate || (digree = BitmapTool.getDigree(path)) == 0) {
                options.inSampleSize = getSize(oriWH);
                return BitmapFactory.decodeFile(path, options);
            }

            options.inSampleSize = getSize((digree == 90 || digree == 270) ? swap(oriWH) : oriWH);
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            Bitmap rotate = BitmapTool.rotate(bitmap, digree);
            if (bitmap != rotate) {
                BitmapTool.destoryBitmap(bitmap);
            }
            return rotate;
        } else {
            if (notRotate || (digree = BitmapTool.getDigree(path)) == 0) {
                options.inSampleSize = getSize(oriWH);
                return cut(options, cut);
            }

            if (digree == 90) {
                cut = new Rect(cut.top, oriWH.h - cut.right, cut.bottom, oriWH.h - cut.left);
            } else if (digree == 180) {
                cut = new Rect(oriWH.w - cut.right, oriWH.h - cut.bottom, oriWH.w - cut.left, oriWH.h - cut.top);
            } else if (digree == 270) {
                cut = new Rect(oriWH.w - cut.bottom, cut.left, oriWH.w - cut.top, cut.right);
            }
            options.inSampleSize = getSize((digree == 90 || digree == 270) ? swap(oriWH) : oriWH);
            Bitmap bitmap = cut(options, cut);
            Bitmap rotate = BitmapTool.rotate(bitmap, digree);
            if (bitmap != rotate)
                BitmapTool.destoryBitmap(bitmap);
            return rotate;
        }
    }

    private WH swap(WH wh) {
        return new WH(wh.h, wh.w);
    }

    private int getSize(WH wh) {
        int size = this.size <= 1 ? 1 : this.size;
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
