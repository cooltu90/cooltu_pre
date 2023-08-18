package cooltu.lib4j.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTool {

    public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YMD = "yyyy-MM-dd";

    private static Date nowDate() {
        return new Date();
    }

    /**************************************************
     *
     * format
     *
     **************************************************/
    public static String format(Date time) {
        return format(FORMAT_DEFAULT, time);
    }

    public static String format(String format, Date time) {
        if (time != null)
            return new SimpleDateFormat(format).format(time);
        return null;
    }

    /***************************************
     *
     * parse
     *
     ***************************************/

    public static Date parse(String dataStr) {
        return parse(FORMAT_DEFAULT, dataStr);
    }

    public static Date parse(String format, String dataStr) {
        try {
            return new SimpleDateFormat(format).parse(dataStr);
        } catch (Exception e) {
        }
        return null;
    }

    /***************************************
     *
     * now
     *
     ***************************************/
    public static String now() {
        return now(FORMAT_DEFAULT);
    }

    public static String now(String format) {
        return format(format, nowDate());
    }

    public static String nowYmd() {
        return ymd(nowDate());
    }

    /***************************************
     *
     * ymd
     *
     ***************************************/

    public static String ymd(Date time) {
        return format(FORMAT_YMD, time);
    }

    public static Date ymdDate(String ymd) {
        return parse(FORMAT_YMD, ymd);
    }

}
