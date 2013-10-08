package com.lxy.tools.NonReflectProxy.ClassOperator;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.lxy.tools.NonReflectProxy.commons.MethodProxyDefine;
import com.lxy.tools.NonReflectProxy.newCode.ICode;
import com.lxy.tools.NonReflectProxy.newCode.NullCode;
import com.lxy.tools.utils.ALLUtils;
import com.lxy.tools.utils.ASMUtils;
import com.lxy.tools.utils.MyStringUtils;
import com.lxy.tools.utils.Pair;

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
	private Class<? extends ICode> beforeProxy;
	private Class<? extends ICode> afterProxy;

	public AddMethodCodeAdapter(MethodVisitor mv, MethodProxyDefine mpd,
			String className) {
		super(mv);
		this.mpd = mpd;
		this.className=className;
		init();
	}
	
	private void init(){
		if(!MyStringUtils.isEmpty(className)){
			className = className.replace(".", "/");
		}
		if(mpd == null){
			return;
		}
		
		Pair<Class<? extends ICode>, Class<? extends ICode>> proxies = mpd.getProxies();
		Class<? extends ICode> proxy = proxies.getFirst();
		if(proxy != null && !proxy.isAssignableFrom(NullCode.class) ){
			beforeProxy = proxy;
		}
		proxy = proxies.getSecond();
		if(proxy != null && !proxy.isAssignableFrom(NullCode.class) ){
			afterProxy = proxy;
		}
	}

	@Override
	public void visitCode(){
		if(beforeProxy != null){
			generateCode(beforeProxy);
		}
	}
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc) {
		super.visitMethodInsn(opcode, owner, name, desc);
		if(afterProxy != null){
			generateCode(afterProxy);
		}
	}

	private void generateCode(Class<? extends ICode> proxy ) {
		if(proxy == null ){
			return;
		}
		String proxyClazzName = proxy.getName().replace(".", "/");
		String fieldName = ALLUtils.getFieldName(proxy);
		mv.visitVarInsn(Opcodes.ALOAD,0 );
		mv.visitFieldInsn(Opcodes.GETFIELD, className, fieldName, Type.getDescriptor(proxy));
		mv.visitVarInsn(Opcodes.ASTORE, 1);
		
		mv.visitVarInsn(Opcodes.ALOAD,1 );
		mv.visitInsn(Opcodes.ACONST_NULL);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,  proxyClazzName, "addCode", ASMUtils.createDesc( Object.class, Object[].class) );
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
