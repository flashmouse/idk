package com.lxy.tools.proxy.configure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.lxy.tools.proxy.annotation.Proxied;
import com.lxy.tools.utils.packageUtil.PackageFilter;

public class ProxiedAnnotationFilter implements PackageFilter {

	public boolean leave(Class<?> clazz) {
		Annotation proxied = clazz.getAnnotation(Proxied.class);
		if(proxied != null){
			return true;
		}
		
		Method [] methods = clazz.getDeclaredMethods();
		for(Method method:methods){
			proxied = method.getAnnotation(Proxied.class);
			if(proxied != null){
				return true;
			}
		}
		
		return false;
	}

}
