package com.lxy.tools.NonReflectProxy.example;

import com.lxy.tools.NonReflectProxy.annotation.Proxied;
import com.lxy.tools.NonReflectProxy.newCode.ExampleCode;

public class ExampleProxied {
	@Proxied(BeforeProxy=ExampleCode.class)
	public void test(){
		System.out.println("原来的函数");
	}
}
