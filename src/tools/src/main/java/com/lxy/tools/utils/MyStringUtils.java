package com.lxy.tools.utils;

public class MyStringUtils {
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }

        return str.trim().isEmpty();
    }

    public static boolean areEmpty(String... strs) {
        boolean flag;
        for (String str : strs) {
            flag = isEmpty(str);
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}
