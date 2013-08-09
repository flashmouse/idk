package com.lxy.tools.asm;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class AddSecurityCheckMethodAdapter extends MethodAdapter {
	public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
		super(mv);
	}

	@Override
	public void visitCode() {
		// visitMethodInsn(Opcodes.INVOKESTATIC,
		// "com/lxy/tools/asm/SecurityChecker",
		// "checkSecurity", "()V");
		visitTypeInsn(Opcodes.NEW, "com/lxy/tools/asm/Hello");
		visitInsn(Opcodes.DUP);
		visitMethodInsn(Opcodes.INVOKESPECIAL, "com/lxy/tools/asm/Hello", "<init>", "()V");
		visitVarInsn(Opcodes.ASTORE, 1);
		visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/lxy/tools/asm/Hello",
				"hello", "()V");
	}
}
