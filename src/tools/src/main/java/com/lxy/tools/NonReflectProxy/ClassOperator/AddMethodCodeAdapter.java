package com.lxy.tools.NonReflectProxy.ClassOperator;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;

import com.lxy.tools.NonReflectProxy.commons.MethodDefine;
import com.lxy.tools.NonReflectProxy.commons.MethodProxyDefine;
import com.lxy.tools.utils.MD5Util;

/**
 * 加入代码
 * 定义每个方法的ICode类的实例的名字:
 * methodName+md5(desc) 32位的md5
 * @author lxy
 *
 */
public class AddMethodCodeAdapter extends MethodAdapter {

	private String className;
	private MethodProxyDefine mpd;

	public AddMethodCodeAdapter(MethodVisitor mv) {
		super(mv);
	}

	public AddMethodCodeAdapter(MethodVisitor mv, MethodProxyDefine mpd,
			String className) {
		super(mv);
		this.mpd = mpd;
	}

	@Override
	public void visitEnd() {
		generateCode(mpd);
	}

	private void generateCode(MethodProxyDefine mpd) {
		MethodDefine md = mpd.getMethodDefine();
		String attrName =getAttrName(mpd.getMethodDefine());
		if(attrName == null){
			return;
		}
		
	}

	private String getAttrName(MethodDefine md) {
		String methodName = md.getName();
		String result = null;
		try {
			result = methodName +MD5Util.md5(md.getDesc());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public MethodProxyDefine getMpd() {
		return mpd;
	}

	public void setMpd(MethodProxyDefine mpd) {
		this.mpd = mpd;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
