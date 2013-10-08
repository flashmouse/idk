package com.lxy.tools.NonReflectProxyTest;

import java.util.List;

import com.lxy.tools.utils.ASMUtils;

public class ASMUtilsTest {
	public static void main(String [] args){
		System.out.println(ASMUtils.createDesc(List.class,Object[].class));
	}
}
