package com.remair.log;

/**
 * 项目名称：LJFramework
 * 类描述：
 * 创建人：liujun
 * 创建时间：2017/8/21 15:45
 * 修改人：liujun
 * 修改时间：2017/8/21 15:45
 * 修改备注：
 */
public interface Log {

    /**
     * verbose输出
     */
    void v(String msg, Object... args);

    void v(Object object);

    /**
     * debug输出
     */
    void d(String msg, Object... args);

    void d(Object object);

    /**
     * info输出
     */
    void i(String msg, Object... args);

    void i(Object object);

    /**
     * warn输出
     */
    void w(String msg, Object... args);

    void w(Object object);

    /**
     * error输出
     */
    void e(String msg, Object... args);

    void e(Object object);

    /**
     * assert输出
     */
    void wtf(String msg, Object... args);

    void wtf(Object object);

    /**
     * 打印json
     */
    void json(String json);

    /**
     * 输出xml
     */
    void xml(String xml);
}
