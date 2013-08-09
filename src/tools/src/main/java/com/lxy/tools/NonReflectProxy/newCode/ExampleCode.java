package com.lxy.tools.NonReflectProxy.newCode;

public class ExampleCode<T> implements ICode<T> {
	protected ICode<?> next;

	public ExampleCode(ICode<?> next) {
		this.next = next;
	}

	public T addCode(Object... paras) throws Exception {
		System.out.println("this is a function before invoke the specified function");
		boolean flag = false;
		if(flag){
			resultHook(null);
		}
		if(next != null){
			next.addCode();
		}
		return null;
	}

	public boolean resultHook(T result) throws Exception {
		return false;
	}

	public ICode<?> getNext() {
		return next;
	}
}
