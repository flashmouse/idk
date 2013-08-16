package com.lxy.tools.NonReflectProxyTest;

import java.lang.reflect.Field;

public class Hello {
	private String hello;
	
	public void test() throws IllegalArgumentException, IllegalAccessException{
		Field[] fs = Hello.class.getDeclaredFields();
		for(Field f:fs){
			System.out.println(f.getName() +":"+ f.get(this).toString() );
		}
	}
}
