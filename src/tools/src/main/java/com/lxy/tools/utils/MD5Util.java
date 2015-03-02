package com.lxy.tools.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: Roger Zhang
 * @create on: 2013-7-23 上午11:46:16
 * @describe: MD5加密返回32位小写密文工具类
 */
public class MD5Util {
    public static String md5(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        // 返回32位大写密文
        return buf.toString().toLowerCase();
    }
}
