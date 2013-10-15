package com.lxy.tools.NonReflectProxy.ClassOperator;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.lxy.tools.NonReflectProxy.newCode.ICode;

public class InitClassFieldsMethodAdapter extends MethodAdapter{

	private Map<String, Class<? extends ICode>> icodesFields;
	private String className;
	
	public InitClassFieldsMethodAdapter(MethodVisitor mv,Map<String, Class<? extends ICode>> icodesFields, String className) {
		super(mv);
		this.icodesFields = icodesFields;
		this.className=className;
	}
	
	@Override
	public void visitInsn(int opcode) {
		if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
				|| opcode == Opcodes.ATHROW) {
			Iterator<Entry<String, Class<? extends ICode>>> it = icodesFields.entrySet().iterator();
			Entry<String,Class<? extends ICode>> entry =null;
			while(it.hasNext()){
				entry = it.next();
				initFields(entry.getKey(), entry.getValue());
			}
		}
		super.visitInsn(opcode);
	}

	private void initFields(String name, Class<? extends ICode> proxy) {
		String clazzName = proxy.getName().replace(".", "/");
		mv.visitTypeInsn(Opcodes.NEW, clazzName);
		mv.visitInsn(Opcodes.DUP);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL,clazzName, "<init>","()V");
		mv.visitVarInsn(Opcodes.ASTORE, 1);
		
		mv.visitVarInsn(Opcodes.ALOAD,0);
		mv.visitVarInsn(Opcodes.ALOAD,1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, className, name, Type.getDescriptor(proxy));
	}

}
