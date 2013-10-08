package com.lxy.tools.utils;

import java.security.NoSuchAlgorithmException;

import com.lxy.tools.NonReflectProxy.newCode.ICode;


public class ALLUtils {
	//给一个ICode代理对象生成一个属性名
	public static String getFieldName(Class<? extends ICode> proxy ) {
		StringBuilder sb = new StringBuilder();
		sb.append(proxy.getSimpleName());
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		String result = null;
		try {
			result = sb.toString() +MD5Util.md5(proxy.getName());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		return result;
	}
}
