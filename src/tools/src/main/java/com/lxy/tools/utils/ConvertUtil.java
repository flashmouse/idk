package com.lxy.tools.utils;

public class ConvertUtil {
    public static Integer parseInt(Object value, Integer defaultValue) {
        try {
            return Integer.parseInt(String.valueOf(value));
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
