package com.lxy.tools.NonReflectProxy.example;

import com.lxy.tools.NonReflectProxy.annotation.Proxied;
import com.lxy.tools.NonReflectProxy.newCode.ExampleAfterCode;
import com.lxy.tools.NonReflectProxy.newCode.ExampleCode;

public class ExampleProxied{
	@Proxied(BeforeProxy=ExampleCode.class,AfterProxy=ExampleAfterCode.class)
	public void test(){
		System.out.println("原来的函数");
	}
	
	@Proxied(BeforeProxy=ExampleCode.class,AfterProxy=ExampleAfterCode.class)
	public void after(){
	}
	
	public ExampleProxied(){
	}
	
	private String a;
}
