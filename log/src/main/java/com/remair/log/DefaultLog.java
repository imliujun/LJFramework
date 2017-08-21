package com.remair.log;

import com.apkfuns.logutils.Log2FileConfig;
import com.apkfuns.logutils.LogConfig;
import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.Printer;
import com.apkfuns.logutils.file.LogFileEngine;
import com.apkfuns.logutils.file.LogFileFilter;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：2017/4/5 16:33
 * 修改人：LiuJun
 * 修改时间：2017/4/5 16:33
 * 修改备注：
 */
public class DefaultLog implements Log {

    public static void init(boolean enabled, String tag) {
        LogConfig logConfig = DefaultLog.getLogConfig();
        if (enabled) {
            logConfig.configAllowLog(true);
            logConfig.configTagPrefix(tag);
            logConfig.configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}");
            logConfig.configShowBorders(true);
            logConfig.configLevel(LogLevel.TYPE_VERBOSE);
            logConfig.configMethodOffset(1);
        } else {
            logConfig.configAllowLog(false);
        }
    }


    public static void initFileConfig(boolean enabled, String fileDir, LogFileEngine engine) {
        DefaultLog.getLog2FileConfig().configLog2FileLevel(LogLevel.TYPE_INFO)
                  .configLog2FilePath(fileDir).configLog2FileNameFormat("%d{yyyyMMdd}.txt")
                  .configLogFileEngine(engine).configLog2FileEnable(enabled);
    }


    /**
     * 自定义对象打印
     */
    public static void addParserClass(Class... parser) {
        //noinspection unchecked
        DefaultLog.getLogConfig().addParserClass(parser);
    }


    /**
     * 设置日志过滤器
     */
    public static void setLogFileFilter(LogFileFilter filter) {
        DefaultLog.getLog2FileConfig().configLogFileFilter(filter);
    }


    /**
     * 日志写入文件相关配置
     */
    private static Log2FileConfig getLog2FileConfig() {
        return com.apkfuns.logutils.LogUtils.getLog2FileConfig();
    }


    public static Printer tag(String tag) {
        return com.apkfuns.logutils.LogUtils.tag(tag);
    }


    /**
     * 选项配置
     */
    public static LogConfig getLogConfig() {
        return com.apkfuns.logutils.LogUtils.getLogConfig();
    }


    /**
     * verbose输出
     */
    public void v(String msg, Object... args) {
        com.apkfuns.logutils.LogUtils.v(msg, args);
    }


    public void v(Object object) {
        com.apkfuns.logutils.LogUtils.v(object);
    }


    /**
     * debug输出
     */
    public void d(String msg, Object... args) {
        com.apkfuns.logutils.LogUtils.d(msg, args);
    }


    public void d(Object object) {
        com.apkfuns.logutils.LogUtils.d(object);
    }


    /**
     * info输出
     */
    public void i(String msg, Object... args) {
        com.apkfuns.logutils.LogUtils.i(msg, args);
    }


    public void i(Object object) {
        com.apkfuns.logutils.LogUtils.i(object);
    }


    /**
     * warn输出
     */
    public void w(String msg, Object... args) {
        com.apkfuns.logutils.LogUtils.w(msg, args);
    }


    public void w(Object object) {
        com.apkfuns.logutils.LogUtils.w(object);
    }


    /**
     * error输出
     */
    public void e(String msg, Object... args) {
        com.apkfuns.logutils.LogUtils.e(msg, args);
    }


    public void e(Object object) {
        com.apkfuns.logutils.LogUtils.e(object);
    }


    /**
     * assert输出
     */
    public void wtf(String msg, Object... args) {
        com.apkfuns.logutils.LogUtils.wtf(msg, args);
    }


    public void wtf(Object object) {
        com.apkfuns.logutils.LogUtils.wtf(object);
    }


    /**
     * 打印json
     */
    public void json(String json) {
        com.apkfuns.logutils.LogUtils.json(json);
    }


    /**
     * 输出xml
     */
    public void xml(String xml) {
        com.apkfuns.logutils.LogUtils.xml(xml);
    }
}
