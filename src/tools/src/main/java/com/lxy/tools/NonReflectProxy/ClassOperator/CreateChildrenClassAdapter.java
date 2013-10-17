package com.lxy.tools.NonReflectProxy.ClassOperator;

import java.util.List;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.lxy.tools.NonReflectProxy.commons.MethodDefine;
import com.lxy.tools.NonReflectProxy.commons.MethodProxyDefine;

public class CreateChildrenClassAdapter extends ClassAdapter implements Opcodes {
	private String className;
	private String superName;
	private List<MethodProxyDefine> mpds;

	public CreateChildrenClassAdapter(ClassVisitor cv,List<MethodProxyDefine> mpds,String superName,String className) {
		super(cv);
		this.className = className;
		this.superName = superName;
		this.mpds = mpds;
		init();
	}
	
	private void init(){
		MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "<init>", "()V", null,null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, superName, "<init>", "()V") ;
		mv.visitInsn(RETURN);
		MethodDefine md;
		for(MethodProxyDefine mpd:mpds ){
			md = mpd.getMethodDefine();
			mv = cv.visitMethod(ACC_PUBLIC, md.getName(), md.getDesc(), null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, superName, md.getName(), md.getDesc());
			mv.visitInsn(RETURN);
		}
	}
	
//	@Override
//	public MethodVisitor visitMethod(final int access, final String name,
//			final String desc, final String signature, final String[] exceptions){
//		MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
//		
////		if(mv == null){
////			return null;
////		}
////		
////		if(name.equals("<init>")){
////			return new ChangeToChildConstructorMethodAdapter(mv,className,superName);
////		}
//		
//		return mv;
//	}
}
