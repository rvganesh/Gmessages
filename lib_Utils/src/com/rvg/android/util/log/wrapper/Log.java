package com.rvg.android.util.log.wrapper;

import android.app.Application;


/**
 * A wrapper around the standard Android Logcat facility that automatically uses the calling class name for the tag, and prefixes the messages with the calling
 * method name.
 */
public class Log {
    private static class CallerInfo {
        public String tag;
        public String method;
    }

    private static String sTagPrefix = "";

    /**
     * This can be called prior to using the other methods of this class, to specify a prefix to prepend to the tag.
     * Typically this should be called in {@link Application#onCreate()}.<br/>
     */
    public static void init(String tagPrefix) {
        sTagPrefix = tagPrefix;
        if (!sTagPrefix.endsWith("/")) sTagPrefix += "/";
    }

    public static void d() {
        CallerInfo callerInfo = getCallerInfo();
        android.util.Log.d(callerInfo.tag, callerInfo.method);
    }

    public static void d(String msg) {
        CallerInfo callerInfo = getCallerInfo();
        android.util.Log.d(callerInfo.tag, callerInfo.method + " " + msg);
    }

    public static void w(String msg) {
        CallerInfo callerInfo = getCallerInfo();
        android.util.Log.w(callerInfo.tag, callerInfo.method + " " + msg);
    }

    public static void w(String msg, Throwable t) {
        CallerInfo callerInfo = getCallerInfo();
        android.util.Log.w(callerInfo.tag, callerInfo.method + " " + msg, t);
    }

    public static void e(String msg, Throwable t) {
        CallerInfo callerInfo = getCallerInfo();
        android.util.Log.e(callerInfo.tag, callerInfo.method + " " + msg, t);
    }


    private static CallerInfo getCallerInfo() {
        CallerInfo res = new CallerInfo();
        StackTraceElement element = Thread.currentThread().getStackTrace()[4];
        res.tag = element.getClassName();
        res.tag = res.tag.substring(res.tag.lastIndexOf('.') + 1);
        res.tag = sTagPrefix + res.tag;
        res.method = element.getMethodName();
        return res;
    }
}
