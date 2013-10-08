package com.lxy.tools.NonReflectProxy.ClassOperator;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ChangeToChildConstructorMethodAdapter extends MethodAdapter {
	private String superClassName;

	public ChangeToChildConstructorMethodAdapter(MethodVisitor mv,
			String superClassName) {
		super(mv);
		this.superClassName = superClassName;
	}
	
	@Override
	public void visitCode(){
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, superClassName, "<init>", "()V");
	}
	
	@Override
	public void visitEnd(){
		
	}
}