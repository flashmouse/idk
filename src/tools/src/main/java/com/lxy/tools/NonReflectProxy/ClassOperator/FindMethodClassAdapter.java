package com.lxy.tools.NonReflectProxy.ClassOperator;

import java.util.List;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.lxy.tools.NonReflectProxy.commons.MethodDefine;
import com.lxy.tools.NonReflectProxy.newCode.ICode;
import com.lxy.tools.utils.Pair;

public class FindMethodClassAdapter extends ClassAdapter {

	private String className;
	private List<Pair<MethodDefine, ICode<?>>> methods;

	public FindMethodClassAdapter(ClassVisitor cv) {
		super(cv);
	}

	public FindMethodClassAdapter(ClassVisitor cv,
			List<Pair<MethodDefine, ICode<?>>> methods) {
		super(cv);
		this.methods = methods;
	}

	// @Override
	// public MethodVisitor visitMethod(final int access, final String name,
	// final String desc, final String signature, final String[] exceptions) {
	// MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
	// exceptions);
	//
	//
	// // MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
	// // exceptions);
	// // MethodVisitor wrappedMv = mv;
	// // if (mv != null) {
	// // MethodDefine md = null;
	// // ICode<?> icode = null;
	// // for (Pair<MethodDefine, ICode<?>> method : methods) {
	// // md = method.getFirst();
	// // if (md.getName().equals(name)) {
	// // wrappedMv = new AddMethodCodeAdapter(wrappedMv, md,
	// // className);
	// // }
	// // }
	//
	// // if (name.equals("operation")) {
	// //
	// // }
	// // else if (name.equals("<init>")) {
	// // wrappedMv = new ChangeToChildConstructorMethodAdapter(mv,
	// // enhancedSuperName);
	// // }
	// // }
	// // return wrappedMv;
	// return null;
	// }

	// @Override
	// public void visit(final int version, final int access, final String name,
	// final String signature, final String superName,
	// final String[] interfaces) {
	// className = name;
	// super.visit(version, access, name, signature, superName, interfaces);
	// }

	@Override
	public void visitEnd() {
		cv.visitField(Opcodes.ACC_PUBLIC, "name",
				Type.getDescriptor(String.class), null, null);
		
		String methodDes = "()V";
		MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC  , "begin", methodDes, null, null);
		mv.visitCode();
		
		mv.visitVarInsn(Opcodes.ALOAD,0 );
		mv.visitLdcInsn("hello");
		mv.visitFieldInsn(Opcodes.PUTFIELD, "com.lxy.tools.NonReflectProxyTest.Hello".replace(".", "/"), "hello", Type.getDescriptor(String.class));
		
		mv.visitVarInsn(Opcodes.ALOAD,0 );
		mv.visitLdcInsn("lxy");
		mv.visitFieldInsn(Opcodes.PUTFIELD, "com.lxy.tools.NonReflectProxyTest.Hello".replace(".", "/"), "name", Type.getDescriptor(String.class));
		
		mv.visitTypeInsn(Opcodes.NEW, "com.lxy.tools.NonReflectProxyTest.MyValue".replace(".", "/"));
		mv.visitInsn(Opcodes.DUP);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "com.lxy.tools.NonReflectProxyTest.MyValue".replace(".", "/"), "<init>", "()V");
		mv.visitVarInsn(Opcodes.ASTORE, 1);
		
		mv.visitVarInsn(Opcodes.ALOAD,1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,  "com.lxy.tools.NonReflectProxyTest.MyValue".replace(".", "/"), "toString", "()"+Type.getDescriptor(String.class));
		
		cv.visitField(Opcodes.ACC_PUBLIC, "value",
				Type.getDescriptor(MyValue.class), null, null);
		
		mv.visitVarInsn(Opcodes.ALOAD,0);
		mv.visitVarInsn(Opcodes.ALOAD,1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, "com.lxy.tools.NonReflectProxyTest.Hello".replace(".", "/"), "value", Type.getDescriptor(MyValue.class));

		mv.visitInsn(Opcodes.RETURN); 
		mv.visitMaxs(1, 2);
		mv.visitEnd();
	}

}
