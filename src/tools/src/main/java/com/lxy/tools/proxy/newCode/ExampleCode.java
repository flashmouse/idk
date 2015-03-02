package com.lxy.tools.proxy.newCode;

public class ExampleCode implements ICode {
    protected ICode next;

    public ExampleCode() {
        next = null;
    }
//	
//	public ExampleCode(ICode next) {
//		this.next = next;
//	}

    public Object addCode(Object... paras) throws Exception {
        System.out.println("this is a function before invoke the specified function");
        boolean flag = false;
        if (flag) {
            resultHook(null);
        }
        if (next != null) {
            next.addCode();
        }
        return null;
    }

    public boolean resultHook(Object result) throws Exception {
        return false;
    }

    public ICode getNext() {
        return next;
    }

    public void addCode() throws Exception {
        System.out.println("this is a function before invoke the specified function");
    }
}
