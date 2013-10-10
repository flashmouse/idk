package com.lxy.tools.NonReflectProxy;

import com.lxy.tools.NonReflectProxy.example.ExampleProxied;

public class Hello {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		StartWork sw = StartWork.getInstance();
		sw.execute();
		
		ExampleProxied ep = (ExampleProxied) NonReflectProxyFactory.getProxiedObject(ExampleProxied.class);
		ep.test();
	}

}
