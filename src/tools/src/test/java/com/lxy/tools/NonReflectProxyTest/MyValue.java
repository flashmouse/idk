package com.lxy.tools.NonReflectProxyTest;

public class MyValue {

	public String value;

	@Override
	public String toString(){
		System.out.println("MyValue:"+(value==null?"null":value));
		return "MyValue:"+(value==null?"null":value);
	}
}
