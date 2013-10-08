package com.lxy.tools.NonReflectProxy.ClassOperator;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import com.lxy.tools.utils.MyStringUtils;

public class ChangeToChildConstructorClassAdapter extends ClassAdapter{

	private String className;

	public ChangeToChildConstructorClassAdapter(ClassVisitor cv, String className) {
		super(cv);
		this.className = className;
		init();
	}

	private void init() {
		if (!MyStringUtils.isEmpty(className)) {
			className = className.replace(".", "/");
		}
	}
	
	@Override
	public MethodVisitor visitMethod(final int access, final String name,
			final String desc, final String signature, final String[] exceptions) {
		MethodVisitor mv = super.visitMethod(access, name, desc, signature,
				exceptions);

		if (mv == null) {
			return null;
		}

		if (name.equals("<init>")) {
			return new ChangeToChildConstructorMethodAdapter(mv,
					className.replace(".", "/"));
		}
		return mv;
	}

}
