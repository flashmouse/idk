package com.lxy.tools.NonReflectProxy.newCode;

public interface ICode<T> {
	T addCode(Object... paras) throws Exception;

	boolean resultHook(T result) throws Exception;
	
	ICode<?> getNext();
}
