package com.codingtu.cooltu.lib4j.log;

import com.codingtu.cooltu.lib4j.config.LibConfigs;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.lib4j.ts.Ts;
import com.codingtu.cooltu.lib4j.ts.impl.BaseTs;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LibLogs {
    //封装了log方法，可以打印多种类型
    public static final int LEVEL_INFO = 0;
    public static final int LEVEL_WARNING = 1;
    public static final int LEVEL_ERROR = 2;

    public static LibConfigs getConfigs() {
        return LibConfigs.configs();
    }

    private static String getDefaultTag() {
        return getConfigs() == null ? "log" : getConfigs().getDefaultLogTag();
    }

    private static String getDefaultTagI() {
        return getDefaultTag() + "_I";
    }

    private static String getDefaultTagE() {
        return getDefaultTag() + "_E";
    }

    private static String getDefaultTagW() {
        return getDefaultTag() + "_W";
    }

    private static boolean isLog() {
        return getConfigs() != null && getConfigs().isLog();
    }

    public static void line(Object... msgs) {
        StringBuilder sb = new StringBuilder();
        sb.append(" \n");
        sb.append(
                "┌───────────────────────────────────────────────────────────────────────────────────────\n");

        for (int i = 0; i < CountTool.count(msgs); i++) {
            sb.append("│ " + msgs[i] + "\n");
        }
        sb.append(
                "└───────────────────────────────────────────────────────────────────────────────────────\n");
        i(sb.toString());
    }

    public static void lineWithTag(String tag, Object... msgs) {
        StringBuilder sb = new StringBuilder();
        sb.append(" \n");
        sb.append(
                "┌───────────────────────────────────────────────────────────────────────────────────────\n");

        for (int i = 0; i < CountTool.count(msgs); i++) {
            sb.append("│ " + msgs[i] + "\n");
        }
        sb.append(
                "└───────────────────────────────────────────────────────────────────────────────────────\n");
        i(tag, sb.toString());
    }

    public static void i(Object msg) {
        log(LEVEL_INFO, getDefaultTagI(), msg);
    }

    public static void e(Object msg) {
        log(LEVEL_ERROR, getDefaultTagE(), msg);
    }

    public static void w(Object msg) {
        log(LEVEL_WARNING, getDefaultTagW(), msg);
    }

    public static void isNull(String name, Object obj) {
        log(LEVEL_INFO, getDefaultTagI(), name + "==null?" + (obj == null));
    }

    public static void i(String tag, Object msg) {
        log(LEVEL_INFO, tag, msg);
    }

    public static void e(String tag, Object msg) {
        log(LEVEL_ERROR, tag, msg);
    }

    public static void w(String tag, Object msg) {
        log(LEVEL_WARNING, tag, msg);
    }

    public static void isNull(String tag, String name, Object obj) {
        log(LEVEL_INFO, tag, name + "==null?" + (obj == null));
    }

    private static <T> void log(int level, String tag, Object msg) {
        if (isLog() && StringTool.isNotBlank(tag)) {
            if (msg == null) {
                logNull(level, tag);
            } else if (msg instanceof String) {
                logString(level, tag, (String) msg);
            } else if (msg instanceof Throwable) {
                logThrowable(level, tag, (Throwable) msg);
            } else if (msg instanceof Collection) {
                logCollection(level, tag, (Collection) msg);
            } else if (msg instanceof Map) {
                logMap(level, tag, (Map) msg);
            } else if (msg.getClass().isArray()) {
                logArray(level, tag, Ts.ts(msg));
            } else {
                logOther(level, tag, msg);
            }
        }
    }

    private static void logOther(int level, String tag, Object msg) {
        baseLog(level, tag, msg.toString());
    }

    private static void logNull(int level, String tag) {
        baseLog(level, tag, "this is null");
    }

    private static void logString(int level, String tag, String msg) {
        baseLog(level, tag, msg);
    }

    private static void logThrowable(int level, String tag, Throwable t) {

        StringBuilder sb = new StringBuilder();
        sb.append(
                "  \n┌──Throwable────────────────────────────────────────────────────────────────────────────\n");
        sb.append("│ " + t.getClass().getName() + ": " + t.getMessage() + "\n");
        StackTraceElement[] stackTrace = t.getStackTrace();
        for (StackTraceElement ste : stackTrace) {
            sb.append("│\tat " + ste + "\n");
        }
        Throwable cause = t.getCause();
        if (cause != null) {
            sb.append("│ Caused by: " + cause.getMessage() + "\n");
            StackTraceElement[] stackTrace1 = cause.getStackTrace();
            for (StackTraceElement ste : stackTrace1) {
                sb.append("│\tat " + ste + "\n");
            }
        }
        sb.append(
                "└───────────────────────────────────────────────────────────────────────────────────────\n");
        baseLog(level, tag, sb.toString());
    }

    private static void logCollection(int level, String tag, Collection cs) {
        baseLog(level, tag, "");
        baseLog(level, tag, "┌──Collection───────────────────────────────────────────────────────────────────────────");
        if (cs.size() <= 0) {
            baseLog(level, tag, "│\tCollection is empty");
        } else {
            Iterator iterator = cs.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                Object next = iterator.next();
                baseLog(level, tag, "│\tindex:" + index + " |" + next);
                index++;
            }
        }
        baseLog(level, tag, "└───────────────────────────────────────────────────────────────────────────────────────");
    }


    private static void logMap(int level, String tag, Map map) {
        baseLog(level, tag, "");
        baseLog(level, tag, "┌──Map────────────────────────────────────────────────────────────────────────────────");
        int count = CountTool.count(map);
        if (count <= 0) {
            baseLog(level, tag, "│\tMap is empty");
        } else {
            Set set = map.keySet();
            for (Object key : set) {
                baseLog(level, tag, "│\tkey:" + key + " | value:" + map.get(key));
            }
        }
        baseLog(level, tag, "└───────────────────────────────────────────────────────────────────────────────────────");
    }

    private static <T> void logArray(int level, String tag, BaseTs<T> getter) {
        baseLog(level, tag, "");
        baseLog(level, tag, "┌──Array────────────────────────────────────────────────────────────────────────────────");
        if (getter == null || getter.count() <= 0) {
            baseLog(level, tag, "│\tArray is empty");
        } else {
            for (int i = 0; i < getter.count(); i++) {
                baseLog(level, tag, "│\tindex:" + i + " | " + getter.get(i));
            }
        }
        baseLog(level, tag, "└───────────────────────────────────────────────────────────────────────────────────────");
    }

    private static void baseLog(int level, String tag, String msg) {
        if (getConfigs() == null) {
            switch (level) {
                case LEVEL_INFO:
                    System.out.println(tag + ":" + msg);
                    break;
                case LEVEL_WARNING:
                case LEVEL_ERROR:
                    System.err.println(tag + ":" + msg);
                    break;
            }
        } else {
            getConfigs().baseLog(level, tag, msg);
        }
    }
}
