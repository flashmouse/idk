package com.lxy.tools.proxy.newCode;

public class ExampleAfterCode implements ICode{

	public void addCode() throws Exception {
		System.out.println("this is afunction after the specified function");
	}

	public boolean resultHook(Object result) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public ICode getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object addCode(Object... paras) throws Exception {
		System.out.println("this is afunction after the specified function");
		return null;
	}

}
