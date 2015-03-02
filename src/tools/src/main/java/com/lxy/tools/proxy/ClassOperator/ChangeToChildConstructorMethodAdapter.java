package com.lxy.tools.proxy.ClassOperator;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ChangeToChildConstructorMethodAdapter extends MethodAdapter {
	private String superClassName;
	private String className;

	public ChangeToChildConstructorMethodAdapter(MethodVisitor mv,String className,
			String superClassName) {
		super(mv);
		this.className = className;
		this.superClassName = superClassName;
	}
	
	@Override
	public void visitCode(){
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, superClassName, "<init>", "()V") ;
	}
}