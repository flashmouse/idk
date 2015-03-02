package com.lxy.tools.proxy.newCode;

public interface ICode {
	Object addCode(Object... paras) throws Exception;
	void addCode() throws Exception;

	boolean resultHook(Object result) throws Exception;
	
	ICode getNext();
}
