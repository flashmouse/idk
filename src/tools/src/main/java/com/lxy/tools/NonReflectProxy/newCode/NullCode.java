package com.lxy.tools.NonReflectProxy.newCode;

public class NullCode<T> implements ICode<T>{

	public T addCode(Object... paras) throws Exception {
		return null;
	}

	public boolean resultHook(T result) throws Exception {
		return false;
	}

	public ICode<?> getNext() {
		return null;
	}

}
