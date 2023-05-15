package com.codingtu.cooltu.lib4a.tools;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.codingtu.cooltu.lib4a.CoreApp;
import com.codingtu.cooltu.lib4a.bean.WH;
import com.codingtu.cooltu.lib4a.image.BitmapWH;
import com.codingtu.cooltu.lib4a.log.Logs;

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

    public static WH getBitmapWH(Bitmap bitmap) {
        return new WH(bitmap.getWidth(), bitmap.getHeight());
    }

    /**************************************************
     *
     * 销毁Bitmap
     *
     **************************************************/
    public static void destoryBitmap(Bitmap bitmap) {
        // 先判断是否已经回收
        if (bitmap != null && !bitmap.isRecycled()) {
            // 回收并且置为null
            bitmap.recycle();
        }
        System.gc();
    }

    /**************************************************
     *
     * 旋转图片
     *
     **************************************************/

    public static Bitmap rotate(Bitmap bm, float digree) {
        Matrix m = new Matrix();
        m.postRotate(digree);
        Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
                bm.getHeight(), m, true);
        return bitmap;
    }

    public static Bitmap rotate(String path, Bitmap bm) {
        int digree = getDigree(path);
        if (digree != 0) {
            // 旋转图片
            bm = rotate(bm, digree);
        }
        return bm;
    }

    /**************************************************
     *
     * 通过限定显示宽高来获取对应的BitmapFactory.Options的inSampleSize
     *
     **************************************************/

    private static BitmapFactory.Options dealOptions(BitmapFactory.Options options, int width, int height) {
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int inSampleSize = 1;

        if (srcHeight > height || srcWidth > width) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / height);
            } else {
                inSampleSize = Math.round(srcWidth / width);
            }
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return options;
    }

    /**************************************************
     *
     * 通过输入流获取指定显示区域的大小的Bimtap
     *
     **************************************************/
    public static Bitmap getBitmap(InputStream ins, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ins, null, options);
        return BitmapFactory.decodeStream(ins, null, dealOptions(options, width, height));
    }

    //会对图片进行缩放，占用内存多，效率低。
    public static Bitmap getBitmapResource(Resources resources, int resourcesId, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resourcesId, options);
        return BitmapFactory.decodeResource(resources, resourcesId, dealOptions(options, width, height));
    }

    //不会对图片进行缩放，占用内存少，效率高。
    public static Bitmap getBitmapResourceByStream(Resources resources, int resourcesId, int width, int height) {
        InputStream ins = resources.openRawResource(resourcesId);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ins, null, options);
        return BitmapFactory.decodeStream(ins, null, dealOptions(options, width, height));
    }

    public static Bitmap getBitmapByAssetsFile(String filePath) {
        Bitmap image = null;
        AssetManager am = CoreApp.APP.getResources().getAssets();
        try {
            InputStream is = am.open(filePath);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Bitmap getBitmap(byte[] data, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        return BitmapFactory.decodeByteArray(data, 0, data.length, dealOptions(options, width, height));
    }

    /**************************************************
     *
     * 通过Drawable获取Bimtap
     *
     **************************************************/

    public static Bitmap getBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**************************************************
     *
     * Bitmap转Drawable
     *
     **************************************************/
    public static Drawable toDrawable(Bitmap bitmap) {
        return new BitmapDrawable(CoreApp.APP.getResources(), bitmap);
    }

    /**************************************************
     *
     * Bitmap转Byte数组
     *
     **************************************************/
    public byte[] toBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**************************************************
     *
     * 通过View获取Bitmap
     *
     **************************************************/
    public static Bitmap getBitmap(View view) {
        // 打开图像缓存
        view.setDrawingCacheEnabled(true);
        // 必须调用measure和layout方法才能成功保存可视组件的截图到png图像文件
        // 测量View大小
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        // 发送位置和尺寸到View及其所有的子View
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        // 获得可视组件的截图
        return view.getDrawingCache();
    }

    /**************************************************
     *
     * 创建一个空的bitmap
     *
     **************************************************/
    public static Bitmap createBitmap(int w, int h) {
        return Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap createBitmap(WH wh) {
        return createBitmap(wh.w, wh.h);
    }

    public static Bitmap createBitmap(Bitmap bitmap) {
        return createBitmap(bitmap.getWidth(), bitmap.getHeight());
    }

    /**************************************************
     *
     * 模糊图片
     *
     **************************************************/
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

    /**************************************************
     *
     * 最终的画Bitmap方法
     *
     **************************************************/
    public static void drawBitmap(Canvas canvas, Bitmap src, Rect srcRect, Rect dstRect) {
        canvas.drawBitmap(src, srcRect, dstRect, null);
    }

    /**************************************************
     *
     * 分割线
     *
     **************************************************/

    /**************************************************
     *
     *
     *
     **************************************************/
    public static void coverAllBitmap(Canvas canvas, Bitmap src) {
        drawBitmap(canvas, src, RectTool.getBitmapRect(src), RectTool.newRect(canvas));
    }

    public static Bitmap coverAllBitmap(Bitmap src, Bitmap dst) {
        coverAllBitmap(new Canvas(dst), src);
        return dst;
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

    /************************************************
     *
     * drawBitmap
     *
     ************************************************/

    public static void drawBitmap(Bitmap dst, Rect dstRect, Bitmap src) {
        drawBitmap(new Canvas(dst), src, RectTool.getBitmapRect(src), dstRect);
    }

    public static void drawBitmap(Bitmap dst, Rect dstRect, Bitmap src, Rect srcRect) {
        drawBitmap(new Canvas(dst), src, srcRect, dstRect);
    }


    public static Bitmap createBitmap(int w, int h, int color, int radius) {
        Bitmap bitmap = createBitmap(w, h);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = DrawTool.getDefaultPaint();
        paint.setColor(color);
        canvas.drawRoundRect(new RectF(0, 0, w, h), radius, radius, paint);
        return bitmap;
    }


    public static Bitmap zoomBitmap(Bitmap src, int w, int h) {
        Bitmap dst = createBitmap(w, h);
        coverAllBitmap(src, dst);
        return dst;
    }

    public static Bitmap zoomBitmap(Bitmap src, WH wh) {
        Bitmap dst = createBitmap(wh);
        coverAllBitmap(src, dst);
        return dst;
    }

    public static Bitmap copy(Bitmap src) {
        return coverAllBitmap(src, createBitmap(src.getWidth(), src.getHeight()));
    }


    public static Bitmap cutBitmap(Bitmap src, Rect srcRect) {
        WH wh = RectTool.getWH(srcRect);
        return getBitmap(createBitmap(wh), RectTool.newRect(wh), src, srcRect);
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


}
