package com.lxy.tools.NonReflectProxyTest.Example;

import com.lxy.tools.proxy.annotation.Proxied;
import com.lxy.tools.proxy.newCode.ExampleCode;

public class RunCode {

	private Integer value;
	
	public Integer getValue(){
		return value;
	}

	@Proxied(BeforeProxy=ExampleCode.class)
	public void test(){
		System.out.println("in runcode");
	}
}
