package com.lxy.tools.NonReflectProxy.ClassOperator;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;

import com.lxy.tools.NonReflectProxy.commons.MethodDefine;
import com.lxy.tools.NonReflectProxy.newCode.ICode;
import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;
import com.sun.xml.internal.ws.org.objectweb.asm.Type;

public class AddMethodCodeAdapter extends MethodAdapter {

	private String className;
	private MethodDefine md;

	public AddMethodCodeAdapter(MethodVisitor mv) {
		super(mv);
	}

	public AddMethodCodeAdapter(MethodVisitor mv, MethodDefine md,
			String className) {
		super(mv);
		this.md = md;
	}

	@Override
	public void visitCode(){
		visitFieldInsn(Opcodes.GETFIELD, className, Type.getType(ICode.class), "()V");
//		visitVarInsn(Opcodes.ALOAD, var);
//		visitMethodInsn(md.getOpcode(), className,Type.getType(ICode.class), md.getDesc());
	}

	public MethodDefine getMd() {
		return md;
	}

	public void setMd(MethodDefine md) {
		this.md = md;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
