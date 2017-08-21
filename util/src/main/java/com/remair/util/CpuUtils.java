package com.remair.util;

import android.content.Context;
import android.os.Build;
import com.remair.log.LogUtils;
import java.io.IOException;
import java.io.InputStream;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：LiuJun
 * 创建时间：2017/3/18 13:32
 * 修改人：LiuJun
 * 修改时间：2017/3/18 13:32
 * 修改备注：
 */
public class CpuUtils {

    public static final String X86 = "x86";
    public static final String X86_64 = "x86_64";
    public static final String ARM64_V8A = "arm64-v8a";
    public static final String ARMEABI_V7A = "armeabi-v7a";
    public static final String ARMEABI = "armeabi";


    /**
     * 获取手机支持的CPU架构
     */
    public static String[] getSupportedAbis() {
        String[] abis;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            abis = Build.SUPPORTED_ABIS;
        } else {
            abis = new String[] { Build.CPU_ABI, Build.CPU_ABI2 };
        }
        return abis;
    }


    public static boolean copyFileFromAssets(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        try {
            InputStream is = context.getAssets().open(fileName);
            FileUtils.writeFileFromIS(path, is, false);
            copyIsFinish = true;
        } catch (IOException e) {
            LogUtils.e("[copyFileFromAssets] IOException %s", e.toString());
            LogUtils.e(e);
        }
        return copyIsFinish;
    }
}
