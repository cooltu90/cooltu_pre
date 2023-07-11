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
     * v1.0.46
     * + 缩放控件增加单击和双击事件
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.47
     * +修改Api中jsonbody的设置。兼容jsonbody是列表的数据
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.48
     * ！！！修改CoreScaleView中onSingleClick和onMultiClick函数的传入参数，由MotionEvent改为P类型
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.49
     * + 为CoreImageView增加圆角扩展（通过setImageDrawable方法设置的图片也可以用圆角）
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.50
     * ！！！ 修改了CoreXXXXView中对圆角的处理
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.51
     * + 更新RoundTextView和RoundEditText的设置stokeWidth方法
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.52
     * +ToastDialog增加获取toast的文本的功能
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.53
     * +增加BreathView和MarqueeTextView
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.54
     * +增加InBase注解，设置此注解可以让ActBase中的字段移入基础Act中
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.55
     * +优化InBase
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.56
     * +修复InBase的类有父类也带有InBase的bug
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.57
     * +增加对基础Act中的点击事件的支持
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.58
     * +对InBaseClickView的bug进行修改
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.59
     * +InBaseClickView中增加inAct的支持
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.60
     * +增加ResForBase
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.61
     * +修改ActBackIntent中toJson的bug
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.62
     * +lib4j增加ts对list的最后一个元素和添加的功能
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.63
     * +lib4j增加Ts重增加分组排序和list的转换
     * +增加InBaseActBack
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.64
     * +引入新的lib4j v1.0.13
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.65
     * +引入新的lib4j v1.0.14
     *
     **************************************************/
}
