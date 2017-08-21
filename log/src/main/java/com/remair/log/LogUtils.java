package com.remair.log;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：2017/4/5 16:33
 * 修改人：LiuJun
 * 修改时间：2017/4/5 16:33
 * 修改备注：
 */
public class LogUtils {

    private static Log sLog;


    public static void setLog(Log log) {
        if (log == null) {
            sLog = new DefaultLog();
        } else {
            sLog = log;
        }
    }


    /**
     * verbose输出
     */
    public static void v(String msg, Object... args) {
        sLog.v(msg, args);
    }


    public static void v(Object object) {
        sLog.v(object);
    }


    /**
     * debug输出
     */
    public static void d(String msg, Object... args) {
        sLog.d(msg, args);
    }


    public static void d(Object object) {
        sLog.d(object);
    }


    /**
     * info输出
     */
    public static void i(String msg, Object... args) {
        sLog.i(msg, args);
    }


    public static void i(Object object) {
        sLog.i(object);
    }


    /**
     * warn输出
     */
    public static void w(String msg, Object... args) {
        sLog.w(msg, args);
    }


    public static void w(Object object) {
        sLog.w(object);
    }


    /**
     * error输出
     */
    public static void e(String msg, Object... args) {
        sLog.e(msg, args);
    }


    public static void e(Object object) {
        sLog.e(object);
    }


    /**
     * assert输出
     */
    public static void wtf(String msg, Object... args) {
        sLog.wtf(msg, args);
    }


    public static void wtf(Object object) {
        sLog.wtf(object);
    }


    /**
     * 打印json
     */
    public static void json(String json) {
        sLog.json(json);
    }


    /**
     * 输出xml
     */
    public static void xml(String xml) {
        sLog.xml(xml);
    }
}
