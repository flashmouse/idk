package com.lxy.tools.NonReflectProxyTest;

import com.lxy.tools.utils.ASMUtils;

import java.util.List;

public class ASMUtilsTest {
    public static void main(String[] args) {
        System.out.println(ASMUtils.createDesc(List.class, Object[].class));
    }
}
