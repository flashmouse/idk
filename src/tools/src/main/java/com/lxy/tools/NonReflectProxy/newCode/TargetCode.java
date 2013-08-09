package com.lxy.tools.NonReflectProxy.newCode;

public class TargetCode<T> implements ICode<T> {
	private ICode<?> next;

	public TargetCode() {

	}

	public TargetCode(ICode<?> next) {
		this.next = next;
	}

	public T addCode(Object... paras) throws Exception {
		return null;
	}

	public boolean resultHook(T result) throws Exception {
		return false;
	}

	public ICode<?> getNext() {
		return next;
	}

}
