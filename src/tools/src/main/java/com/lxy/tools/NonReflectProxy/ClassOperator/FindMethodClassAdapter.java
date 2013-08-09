package com.lxy.tools.NonReflectProxy.ClassOperator;

import java.util.List;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import com.lxy.tools.NonReflectProxy.commons.MethodDefine;
import com.lxy.tools.NonReflectProxy.newCode.ICode;
import com.lxy.tools.commons.Pair;

public class FindMethodClassAdapter extends ClassAdapter {

	private String className;
	private List<Pair<MethodDefine, ICode<?>>> methods;

	private FindMethodClassAdapter(ClassVisitor cv) {
		super(cv);
	}

	public FindMethodClassAdapter(ClassVisitor cv,
			List<Pair<MethodDefine, ICode<?>>> methods) {
		super(cv);
		this.methods = methods;
	}

	@Override
	public MethodVisitor visitMethod(final int access, final String name,
			final String desc, final String signature, final String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
				exceptions);
		MethodVisitor wrappedMv = mv;
		if (mv != null) {
			MethodDefine md = null;
			ICode<?> icode = null;
			for (Pair<MethodDefine, ICode<?>> method : methods) {
				md = method.getFirst();
				if (md.getName().equals(name)) {
					wrappedMv = new AddMethodCodeAdapter(wrappedMv, md,
							className);
				}
			}
			// if (name.equals("operation")) {
			//
			// }
			// else if (name.equals("<init>")) {
			// wrappedMv = new ChangeToChildConstructorMethodAdapter(mv,
			// enhancedSuperName);
			// }
		}
		return wrappedMv;
	}

	@Override
	public void visit(final int version, final int access, final String name,
			final String signature, final String superName,
			final String[] interfaces) {
		className = name;
		super.visit(version, access, name, signature, superName, interfaces);
	}

}
