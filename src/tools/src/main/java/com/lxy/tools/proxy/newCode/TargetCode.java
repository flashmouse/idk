package com.lxy.tools.proxy.newCode;

public class TargetCode implements ICode {
    private ICode next;

    public TargetCode() {

    }

    public TargetCode(ICode next) {
        this.next = next;
    }

    public Object addCode(Object... paras) throws Exception {
        return null;
    }

    public boolean resultHook(Object result) throws Exception {
        return false;
    }

    public ICode getNext() {
        return next;
    }

    public void addCode() throws Exception {
        // TODO Auto-generated method stub

    }

}
