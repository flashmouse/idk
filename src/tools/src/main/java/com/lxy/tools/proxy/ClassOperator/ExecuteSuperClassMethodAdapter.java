package com.lxy.tools.proxy.ClassOperator;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ExecuteSuperClassMethodAdapter extends MethodAdapter {
    private String methodName;

    private Integer place;

    private Integer op;

    private String desc;

    private String superClassName;

    /**
     * @param mv
     * @param methodName
     * @param place      1在函数开始时执行super 2在函数返回前执行super
     */
    public ExecuteSuperClassMethodAdapter(MethodVisitor mv,
                                          String superClassName, String methodName, Integer place, int op,
                                          String desc) {
        super(mv);
        this.methodName = methodName;
        this.place = place;
        this.op = op;
        this.desc = desc;
        this.superClassName = superClassName;
    }

    @Override
    public void visitCode() {
        if (place == 1) {
            visitSuper();
        }
        super.visitCode();
    }

    private void visitSuper() {
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(op, superClassName, methodName, desc);
    }

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
                || opcode == Opcodes.ATHROW) {
            if (place == 2) {
                visitSuper();
            }
        }
        super.visitInsn(opcode);
    }
}
