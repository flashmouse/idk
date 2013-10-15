package com.lxy.tools.NonReflectProxy.ClassOperator;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CreateChildrenClassAdapter extends ClassAdapter implements Opcodes {
	private String className;
	private String superName;

	public CreateChildrenClassAdapter(ClassVisitor cv,String superName,String className) {
		super(cv);
		this.className = className;
		this.superName = superName;
	}
	
	@Override
	public MethodVisitor visitMethod(final int access, final String name,
			final String desc, final String signature, final String[] exceptions){
		MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
		
		if(mv == null){
			return null;
		}
		
		if(name.equals("<init>")){
			return new ChangeToChildConstructorMethodAdapter(mv,className,superName);
		}
		
		return mv;
	}
}
