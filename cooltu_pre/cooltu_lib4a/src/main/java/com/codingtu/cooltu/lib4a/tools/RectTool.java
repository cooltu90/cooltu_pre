package com.codingtu.cooltu.lib4a.tools;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.codingtu.cooltu.lib4a.bean.WH;
import com.codingtu.cooltu.lib4a.log.Logs;

public class RectTool {

    public static Rect newRect(int w, int h) {
        return new Rect(0, 0, w, h);
    }

    public static Rect newRect(WH wh) {
        return newRect(wh.w, wh.h);
    }

    public static Rect getBitmapRect(Bitmap bitmap) {
        return newRect(bitmap.getWidth(), bitmap.getHeight());
    }

    public static WH getWH(Rect rect) {
        return new WH(rect.width(), rect.height());
    }

    public static Rect getCenterRect(int containerW, int containerH, int w, int h) {
        Rect rect = new Rect();
        rect.left = (containerW - w) >> 1;
        rect.top = (containerH - h) >> 1;
        rect.right = rect.left + w;
        rect.bottom = rect.top + h;
        return rect;
    }

    public static void log(Rect rect) {
        Logs.i("rect[l:"+rect.left+" r:"+rect.right+" t:"+rect.top+" b:"+rect.bottom+" w:"+rect.width()+" h:"+rect.height()+"]");
    }
}
