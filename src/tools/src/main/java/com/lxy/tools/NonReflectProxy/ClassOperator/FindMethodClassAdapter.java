package com.lxy.tools.NonReflectProxy.ClassOperator;

import java.util.List;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.lxy.tools.NonReflectProxy.commons.MethodDefine;
import com.lxy.tools.NonReflectProxy.commons.MethodProxyDefine;
import com.lxy.tools.NonReflectProxy.newCode.ICode;
import com.lxy.tools.utils.Pair;


/**
 * 在一个类中找到需要加代理的函数，给他加上眼药水。。
 * @author lxy
 *
 */
public class FindMethodClassAdapter extends ClassAdapter {

	private String className;
	private List<MethodProxyDefine> mpds;

	public FindMethodClassAdapter(ClassVisitor cv) {
		super(cv);
	}

	public FindMethodClassAdapter(ClassVisitor cv,
			List<MethodProxyDefine> mpds) {
		super(cv);
		this.mpds = mpds;
	}

	@Override
	 public MethodVisitor visitMethod(final int access, final String name,
	 final String desc, final String signature, final String[] exceptions) {
		MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);  
		MethodDefine md = null;
		for(MethodProxyDefine mpd:mpds){
			md = mpd.getMethodDefine();
			if(md.getName().equals(name)){
				if(md.getDesc().equals(desc)){
					return new AddMethodCodeAdapter(mv, mpd, className);
				}
			}
		}
		
		return mv;
	}

}
