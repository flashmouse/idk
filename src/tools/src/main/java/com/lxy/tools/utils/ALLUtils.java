package com.lxy.tools.utils;

import com.lxy.tools.proxy.newCode.ICode;

import java.net.URL;
import java.security.NoSuchAlgorithmException;


public class ALLUtils {
    //给一个ICode代理对象生成一个属性名
    public static String getFieldName(Class<? extends ICode> proxy) {
        StringBuilder sb = new StringBuilder();
        sb.append(proxy.getSimpleName());
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        String result = null;
        try {
            result = sb.toString() + MD5Util.md5(proxy.getName());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取当前ClassLoader所在的运行环境的绝对路径
     *
     * @return
     */
    public static URL getContextPath() {
        ClassLoader cl = ALLUtils.class.getClassLoader();
        return cl.getResource("");
    }
}
