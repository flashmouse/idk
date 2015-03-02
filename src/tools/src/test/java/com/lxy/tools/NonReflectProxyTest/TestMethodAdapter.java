package com.lxy.tools.NonReflectProxyTest;

import java.util.List;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.lxy.tools.proxy.commons.MethodDefine;
import com.lxy.tools.proxy.newCode.ICode;
import com.lxy.tools.utils.Pair;

public class TestMethodAdapter extends ClassAdapter {

	private String className;
	private List<Pair<MethodDefine, ICode>> methods;

	public TestMethodAdapter(ClassVisitor cv) {
		super(cv);
	}

	public TestMethodAdapter(ClassVisitor cv,
			List<Pair<MethodDefine, ICode>> methods) {
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
	}

	private void method1() {
		//生成一个属性 name
		cv.visitField(Opcodes.ACC_PUBLIC, "name",
				Type.getDescriptor(String.class), null, null);
		
		//生成一个无参返回void的名为begin的public方法
		String methodDes = "()V";
		MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC  , "begin", methodDes, null, null);
		mv.visitCode();
		
		//取出第一个变量 即this
		mv.visitVarInsn(Opcodes.ALOAD,0 );
		//设置一个常量 值为hello
		mv.visitLdcInsn("hello");
		//对Hello了哦的hello属性,设置值,为上一个常量
		mv.visitFieldInsn(Opcodes.PUTFIELD, "com.lxy.tools.NonReflectProxyTest.Hello".replace(".", "/"), "hello", Type.getDescriptor(String.class));
		
		//取出this
		mv.visitVarInsn(Opcodes.ALOAD,0 );
		//设置常量值为lxy
		mv.visitLdcInsn("lxy");
		//Hello类的name属性设置值为上一个常量
		mv.visitFieldInsn(Opcodes.PUTFIELD, "com.lxy.tools.NonReflectProxyTest.Hello".replace(".", "/"), "name", Type.getDescriptor(String.class));
		
		//新声明一个MyValue类的实例
		mv.visitTypeInsn(Opcodes.NEW, "com.lxy.tools.NonReflectProxyTest.MyValue".replace(".", "/"));
		//将它复制入栈
		mv.visitInsn(Opcodes.DUP);
		//执行MyValue类的构造函数
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "com.lxy.tools.NonReflectProxyTest.MyValue".replace(".", "/"), "<init>", "()V");
		//将其放入参数表第2个位置
		mv.visitVarInsn(Opcodes.ASTORE, 1);
		
		//访问参数表第二个位置,即MyValue的实例
		mv.visitVarInsn(Opcodes.ALOAD,1);
		//执行方法上行代码取出的实例的toString方法
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,  "com.lxy.tools.NonReflectProxyTest.MyValue".replace(".", "/"), "toString", "()"+Type.getDescriptor(String.class));
		
		//给this类加一个public的MyVaue类型的参数,名为value
		cv.visitField(Opcodes.ACC_PUBLIC, "value",
				Type.getDescriptor(MyValue.class), null, null);
		
		//访问this
		mv.visitVarInsn(Opcodes.ALOAD,0);
		//取到MyValue的实例
		mv.visitVarInsn(Opcodes.ALOAD,1);
		//将MyValue实例放到value属性
		mv.visitFieldInsn(Opcodes.PUTFIELD, "com.lxy.tools.NonReflectProxyTest.Hello".replace(".", "/"), "value", Type.getDescriptor(MyValue.class));

		//返回return
		mv.visitInsn(Opcodes.RETURN); 
		mv.visitMaxs(1, 2);
		mv.visitEnd();
	}

}
