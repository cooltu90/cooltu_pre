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
    /**************************************************
     *
     * v1.0.66
     * +增加To注解的使用范围，增加了ids字段
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.67
     * ！！！修改StartGroup在InBase中的顺序，先Base中的再是子类中的
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.68
     * ！！！修改StartGroup在InBase中的顺序，从最底层的父类开始，一直往上
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.69
     * ++对TextView增加设置类。简化设置
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.70
     * ++TextViewSetter可以对view强转TextView，强转失败则抛出异常
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.71
     * ++增加对ConnectConfigs的支持
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.72
     * ++增加BitmapTool中blur方法的覆写方法，在参数列表中增加Context，解决引用的自定义view无法预览的问题
     * ++ToastDialog增加返回和阴影点击关闭的设置
     * ++Dialog中增加对contentTv的动态设置功能
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.73
     * ++Dialog中contentTv的bug修改
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.74
     * ++Net框架中的bug修改，在Apis中的方法中单独设置BaseUrl会出错。
     * !! lib4j框架 修改ConvertTool删除float和byte数组等的转换
     * ++ lib4j框架 增加float和byte数组等的转换类。FloatValue类等。
     * ++ connect的框架增加
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.75
     * ++Connect修改bonded方法
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.76
     * ++增加connect对wifi设备的支持
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.77
     * + 对ConnectDevice中uuid传入做了校验
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.78
     * + 删除SocketConnectDevice中connect的super方法
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.79
     * + 修改cooltu4j的新版本
     * + 修改Connect相关的功能
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.80
     * ！修改Connect相关的功能
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.81
     * + 删除app中的错误代码
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.82
     * !!! 删除ConnectDevice的一个构造方法
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.83
     * !!! 修改ConnectTools、ConnectConfigs等方法，主要用于蓝牙绑定的配置
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.84
     * ++ ConnectTool方法cacheConnectDeviceBaseData变为静态
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.85
     * !!! 修改connect
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.86
     * !!! 修改connect
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.87
     * ++ 修改lib4j 1.0.18
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.88
     * ++ 修改lib4j 1.0.19
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.89
     * ++ 修改lib4j 1.0.20
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.90
     * ++ 修改lib4j 1.0.21
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.91
     * ++ 把lib4j源代码加入框架
     *
     **************************************************/
    /**************************************************
     *
     * v1.0.92
     * ！！！去掉外部引用的lib4j，改为内源
     *
     **************************************************/
    /**************************************************
     *
     * v1.1.5
     * 修改connect相关方法
     *
     * v1.1.6
     * ConnectTool中的removeCallBack变成公有
     *
     * v1.1.7
     * 修改wificonnect的连接超时
     *
     * v1.1.8
     * 修改ToastDialog和EditDialog
     *
     * v1.1.9
     * 添加NoticeDialog
     *
     * v1.1.10
     * Dialog的生成支持在父类Act或Fragment中设置
     *
     * v1.1.12
     * RadioGroup增加禁用
     *
     * v1.1.13
     * !!!修改Dialog中的点击事件
     *
     * v1.1.16
     * +++修改FileBitmap中size(w,h)中的换算错误
     * +++对ts增加sort方法
     *
     * v1.1.17
     * +++修改ts中sort方法的bug
     *
     * v1.1.18
     * +++修改api的log
     *
     * v1.1.19
     * +++增加Path的框架
     * ！！修改了FileType
     *
     * v1.1.20
     * +++修改Paths的设置
     *
     * v1.1.25
     * +++修改Paths的模板
     *
     * v1.1.26
     * +++增加生成Paths前先清空相应文件
     *
     * v1.1.27
     * +++增加Path的文件列表获取
     *
     * v1.1.28
     * +++修改Path的文件列表获取bug
     *
     * v1.1.29
     * +++增加调用本地应用获取图片视频（相册，相机）
     *
     * v1.1.30
     * +++增加调用本地应用获取图片视频（相册，相机）修改bug
     *
     * v1.1.33
     * +++download 修改
     *
     * v1.1.34
     * +++增加zip和unzip功能
     *
     * v1.1.35
     * +++修改download，zip和unzip的方法
     *
     * v1.1.36
     * +++修改download未destroy的bug
     *
     * v1.1.37
     * +++修改download的OnFinish方法
     *
     * v1.1.38
     * +++修改unzip中的finish事件
     *
     * v1.1.39
     * ！！！增加Upload，修改Download Zip 和UnZip的包
     *
     * v1.1.40
     * +++修改Zip
     *
     * v1.1.41
     * !!!修改Zip
     *
     * v1.1.42
     * !!!修改Zip
     *
     * v1.1.43
     * +++修改upload
     *
     * v1.1.44
     * +++修改LayerView的hidden方法
     *
     * v1.1.45
     * +++修改LayerView的hidden方法
     *
     * v1.1.46
     * +++修改重连时候的bug
     *
     * v1.1.47
     * +++修改connect返回读取数据多次解析的问题
     *
     * v1.1.49
     * +++修改LayerView的hidden问题
     *
     * v1.1.50
     * +++修改LayerView的hidden问题
     *
     * v1.1.51
     * +++修改LayerView的hidden问题
     *
     *
     **************************************************/
}
