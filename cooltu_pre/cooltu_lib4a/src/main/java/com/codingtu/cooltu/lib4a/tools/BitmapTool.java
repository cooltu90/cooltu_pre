package com.codingtu.cooltu.lib4a.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import java.io.File;
import java.io.IOException;

import com.codingtu.cooltu.lib4a.CoreApp;
import com.codingtu.cooltu.lib4a.bean.WH;

public class BitmapTool {
    /**************************************************
     *
     * 获取Bitmap的方向
     *
     **************************************************/

    public static int getDigree(String path) {
        int digree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
            exif = null;
        }
        if (exif != null) {
            // 读取图片中相机方向信息
            int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            // 计算旋转角度
            switch (ori) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    digree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    digree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    digree = 270;
                    break;
                default:
                    digree = 0;
                    break;
            }
        }
        return digree;
    }

    /************************************************
     *
     * 获取Bitmap的宽高
     *
     ************************************************/
    public static WH getBitmapWH(String path) {
        BitmapFactory.Options ops = new BitmapFactory.Options();
        ops.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, ops);
        return new WH(ops.outWidth, ops.outHeight);
    }

    public static WH getBitmapWH(File imageFile) {
        return getBitmapWH(imageFile.getAbsolutePath());
    }

    public static WH getBitmapWH(Bitmap bitmap) {
        return new WH(bitmap.getWidth(), bitmap.getHeight());
    }

    public static WH getBitmapWHWithRotate(String path) {
        int digree = getDigree(path);
        WH wh = getBitmapWH(path);
        if (digree == 90 || digree == 270) {
            // 旋转图片
            wh = swap(wh);
        }
        return wh;
    }

    /************************************************
     *
     * 通过文件获得Bitmap
     *
     ************************************************/

    public static Bitmap getBitmap(String path) {
        return BitmapFactory.decodeFile(path);
    }

    public static Bitmap getBitmap(File file) {
        return getBitmap(file.getAbsolutePath());
    }

    public static Bitmap getBitmap(File file, int size) {
        return getBitmap(file.getAbsolutePath(), size);
    }

    public static Bitmap getBitmap(String path, int size) {
        BitmapFactory.Options ops = new BitmapFactory.Options();
        ops.inSampleSize = size;
        return BitmapFactory.decodeFile(path, ops);
    }


    public static Bitmap getBitmap(String path, Rect rect) throws IOException {
        BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(path, false);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return decoder.decodeRegion(rect, options);
    }

    public static Bitmap getBitmap(File file, Rect rect) throws IOException {
        return getBitmap(file.getAbsolutePath(), rect);
    }

    public static Bitmap getBitmapWithRotate(String path) {
        int digree = getDigree(path);
        Bitmap bm = getBitmap(path);
        if (digree != 0) {
            // 旋转图片
            Matrix m = new Matrix();
            m.postRotate(digree);
            bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
                    bm.getHeight(), m, true);
        }
        return bm;
    }

    /***************************************
     *
     * 创建bitmap
     *
     ***************************************/

    public static Bitmap createBitmap(int w, int h) {
        return Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap createBitmap(WH wh) {
        return createBitmap(wh.w, wh.h);
    }

    public static Bitmap createBitmap(Bitmap bitmap) {
        return createBitmap(bitmap.getWidth(), bitmap.getHeight());
    }

    /***************************************
     *
     * 画bitmap
     *
     ***************************************/
    public static Canvas drawBitmap(Bitmap src) {
        return drawBitmap(src, createBitmap(src));
    }

    public static Canvas drawBitmap(Bitmap src, Bitmap target) {
        Canvas canvas = new Canvas(target);
        drawBitmap(canvas, src);
        return canvas;
    }

    public static void drawBitmap(Canvas canvas, Bitmap src) {
        Rect bitmapRect = RectTool.getBitmapRect(src);
        drawBitmap(canvas, src, bitmapRect, bitmapRect);
    }

    public static void drawBitmap(Canvas canvas, Bitmap src, Rect srcRect, Rect dstRect) {
        canvas.drawBitmap(src, srcRect, dstRect, null);
    }

    /***************************************
     *
     * 分割线
     *
     ***************************************/

    /************************************************
     *
     * drawBitmap
     *
     ************************************************/


//    public static void drawBitmap(Canvas canvas, Rect dstRect, Bitmap src, Rect srcRect) {
//        canvas.drawBitmap(src, srcRect, dstRect, null);
//    }
    public static void drawBitmap(Bitmap dst, Rect dstRect, Bitmap src) {
        drawBitmap(new Canvas(dst), src, RectTool.getBitmapRect(src), dstRect);
    }

    public static void drawBitmap(Bitmap dst, Rect dstRect, Bitmap src, Rect srcRect) {
        drawBitmap(new Canvas(dst), src, srcRect, dstRect);
    }


    public static Bitmap createBitmap(int w, int h, int color, int radius) {
        Bitmap bitmap = createBitmap(w, h);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(new RectF(0, 0, w, h), radius, radius, paint);
        return bitmap;
    }


    public static Bitmap zoomBitmap(Bitmap src, int w, int h) {
        Bitmap dst = createBitmap(w, h);
        coverAllBitmap(dst, src);
        return dst;
    }

    public static Bitmap zoomBitmap(Bitmap src, WH wh) {
        Bitmap dst = createBitmap(wh);
        coverAllBitmap(dst, src);
        return dst;
    }

    public static Bitmap copy(Bitmap src) {
        return coverAllBitmap(createBitmap(src.getWidth(), src.getHeight()), src);
    }


    public static Bitmap cutBitmap(Bitmap src, Rect srcRect) {
        WH wh = RectTool.getWH(srcRect);
        return getBitmap(createBitmap(wh), RectTool.newRect(wh), src, srcRect);
    }

    public static Bitmap coverAllBitmap(Bitmap dst, Bitmap src) {
        drawBitmap(new Canvas(dst), src, RectTool.getBitmapRect(src), RectTool.getBitmapRect(dst));
        return dst;
    }

    public static Bitmap coverBitmap(Bitmap dst, Bitmap src) {
        Rect srcRect = RectTool.getBitmapRect(src);
        drawBitmap(new Canvas(dst), src, srcRect, srcRect);
        return dst;
    }


    public static Bitmap extensionBitmap(Bitmap src, int w) {
        return extensionBitmap(src, w, w);
    }

    public static Bitmap extensionBitmap(Bitmap src, int w, int h) {
        return coverBitmap(createBitmap(w, h), src);
    }

    //////////////////////////////////////////////////


    public static Bitmap getBitmap(Bitmap dst, Rect dstRect, Bitmap src) {
        drawBitmap(new Canvas(dst), src, RectTool.getBitmapRect(src), dstRect);
        return dst;
    }

    public static Bitmap getBitmap(Bitmap dst, Rect dstRect, Bitmap src, Rect srcRect) {
        drawBitmap(new Canvas(dst), src, srcRect, dstRect);
        return dst;
    }

    public static void drawBitmap(Canvas canvas, Rect dstRect, Bitmap src) {
        drawBitmap(canvas, src, RectTool.getBitmapRect(src), dstRect);
    }

    public static Bitmap blur(Bitmap image) {
        return blur(image, 1f, 25f);
    }

    public static Bitmap blur(Bitmap image, float BITMAP_SCALE, float BLUR_RADIUS) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
        RenderScript rs = RenderScript.create(CoreApp.APP);
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        blurScript.setRadius(BLUR_RADIUS);
        blurScript.setInput(tmpIn);
        blurScript.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }

    public static WH swap(WH wh) {
        return new WH(wh.h, wh.w);
    }
}
