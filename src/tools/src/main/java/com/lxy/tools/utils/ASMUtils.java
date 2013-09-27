package com.lxy.tools.utils;

import java.lang.reflect.Method;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

public class ASMUtils {
	public static String createDesc(Method method){
		StringBuilder sb = new StringBuilder();
		Class<?> returnType = method.getReturnType();
		Class<?>[] classes = method.getParameterTypes();
		sb.append("(");
		if(classes != null){
			for(Class<?> clazz:classes){
				sb.append(Type.getDescriptor(clazz));
			}
		}
		sb.append(")").append(Type.getDescriptor(returnType));
		
		return sb.toString();
	}
}
