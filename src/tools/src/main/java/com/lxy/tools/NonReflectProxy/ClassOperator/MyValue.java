package com.lxy.tools.NonReflectProxy.ClassOperator;

public class MyValue {

	public String value;

	@Override
	public String toString(){
		return "MyValue:"+(value==null?"null":value);
	}
}
