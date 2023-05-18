package com.codingtu.cooltu.constant;

public class VersionInfo {

    /**************************************************
     *
     * v1.0.35
     * !!!修改FormCheck，之前的将不兼容
     *
     **************************************************/

    /**************************************************
     *
     * v1.0.40
     * !!!删除RoundImageView
     * !!!BitmapTool中修改：
     *    1、swap方法转为私有
     *    2、删除Bitmap getBitmap(String path, int size)
     *          Bitmap getBitmap(File file, int size)
     * +增加新的缩放图片控件
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.41
     * !!!修改BitmapTool 中 Bitmap getBitmap(Bitmap dst, Rect dstRect, Bitmap src)方法
     *
     **************************************************/

    /**************************************************
     *
     * v1.0.42
     * + DefaultScaleImageView中onDraw的测试代码删除
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.43
     * + 缩放控件增加背景色设置
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.44
     * + 缩放控件增加onMoveSingleStart方法
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.45
     * + 缩放控件增加单击和双击事件
     *
     **************************************************/
}
