package com.lxy.tools.NonReflectProxy;

import com.lxy.tools.utils.ByteArrayClassLoader;

public class NonReflectProxyFactory {
	public static ByteArrayClassLoader cl =  new ByteArrayClassLoader(NonReflectProxyFactory.class.getClassLoader());;
	
	public static Object getProxiedObject(Class<?> clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		String fullName = clazz.getName()+"$proxy";
		Class<?> enhancedClass = cl.loadClass(fullName);
		if(enhancedClass == null){
			return null;
		}
		return enhancedClass.newInstance();
	}
}
