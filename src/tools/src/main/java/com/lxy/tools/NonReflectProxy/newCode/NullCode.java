package com.lxy.tools.NonReflectProxy.newCode;

public class NullCode implements ICode{

	public Object addCode(Object... paras) throws Exception {
		return null;
	}

	public boolean resultHook(Object result) throws Exception {
		return false;
	}

	public ICode getNext() {
		return null;
	}

	public void addCode() throws Exception {
	}

}
