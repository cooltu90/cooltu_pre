package core.lib4a.tools;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;

import java.lang.reflect.Field;

import cooltu.lib4j.tools.MathTool;
import core.lib4a.CoreApp;

public class MobileTool {

    /************************************************
     *
     * 获取版本号
     *
     ************************************************/

    public static long getAppVersionCode(Context context) {
        long appVersionCode = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                appVersionCode = packageInfo.getLongVersionCode();
            } else {
                appVersionCode = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
        }
        return appVersionCode;
    }

    public static String getAppVersionName(Context context) {
        String appVersionCode = "";
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            appVersionCode = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
        }
        return appVersionCode;
    }


    /****************************************************************
     *
     * 获取屏幕宽度
     *
     ****************************************************************/

    private static Point point;

    private static int screenWidth = -1;
    /****************************************************************
     *
     * 获取屏幕高度
     *
     ****************************************************************/

    private static int screenHight = -1;
    /****************************************************************
     *
     * 获取屏幕密度
     *
     ****************************************************************/

//    private static float density = -1;

    /****************************************************************
     *
     * 获取屏幕密度
     *
     ****************************************************************/
    private static int statusBarHeight = -1;

    public static int getScreenWidth() {
        if (screenWidth < 0) {
            screenWidth = getScreenPoint().x;
        }
        return screenWidth;
    }

    public static int getScreenHight() {
        if (screenHight < 0) {
            screenHight = getScreenPoint().y;
        }
        return screenHight;
    }

    public static int getScreenMinWidth() {
        int screenWidth = getScreenWidth();
        int screenHight = getScreenHight();
        return screenWidth > screenHight ? screenHight : screenWidth;
    }


//    public static float getDensity() {
//        if (density < 0) {
//            density = getDisplayMetrics().density;
//        }
//        return density;
//    }

    private static Point getScreenPoint() {
        if (point == null) {
            point = new Point();
            SystemTool.getWindowManager().getDefaultDisplay().getRealSize(point);
        }
        return point;
    }

    public static int getStatusBarHeight() {

        if (statusBarHeight < 0) {

            Class<?> c = null;
            Object obj = null;
            Field field = null;
            int x = 0;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = CoreApp.APP.getResources().getDimensionPixelSize(x);
            } catch (Exception e1) {
                statusBarHeight = 0;
            }
        }

        return statusBarHeight;
    }

    public static int dpToEvenPx(float dp) {
        return dpToEvenPx(CoreApp.APP, dp);
    }

    public static int dpToEvenPx(Context context, float dp) {
        return MathTool.toEven(dpToPx(context, dp));
    }

    public static int dpToPx(float dp) {
        return dpToPx(CoreApp.APP, dp);
    }

    public static int dpToPx(Context context, float dp) {
        int v = (int) dpToPxFloat(context, dp);
        return v < 1 ? 1 : v;
    }

    public static float dpToPxFloat(float dp) {
        return dpToPxFloat(CoreApp.APP, dp);
    }

    public static float dpToPxFloat(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getDisplayMetrics(context));
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /****************************************************************
     *
     * 获取输入法高度
     *
     ****************************************************************/

    public static void getRestKeyBoardHeight(Activity act, View view,
                                             OnGetRestKeyBoardHeight onGetRestKeyBoardHeight) {
        view.getViewTreeObserver()
                .addOnGlobalLayoutListener(new OnKeyBoardGlobalLayoutListener(act,
                        view,
                        onGetRestKeyBoardHeight));
    }

    private static class OnKeyBoardGlobalLayoutListener
            implements ViewTreeObserver.OnGlobalLayoutListener {

        private OnGetRestKeyBoardHeight onGetRestKeyBoardHeight;
        private View view;
        private Activity act;

        public OnKeyBoardGlobalLayoutListener(Activity act, View view,
                                              OnGetRestKeyBoardHeight onGetRestKeyBoardHeight) {
            this.view = view;
            this.act = act;
            this.onGetRestKeyBoardHeight = onGetRestKeyBoardHeight;
        }

        @Override
        public void onGlobalLayout() {
            int windowVisibleDisplayH = getWindowVisibleDisplayH(act);
            if (windowVisibleDisplayH != getScreenHight()) {
                onGetRestKeyBoardHeight.onGetRestKeyBoardHeight(windowVisibleDisplayH);
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                deleteData();
            }
        }

        public void deleteData() {
            view = null;
            act = null;
            onGetRestKeyBoardHeight = null;
        }
    }

    public static interface OnGetRestKeyBoardHeight {
        public void onGetRestKeyBoardHeight(int height);
    }

    public static int getWindowVisibleDisplayH(Activity act) {
        Rect rect = new Rect();
        act.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }

}
