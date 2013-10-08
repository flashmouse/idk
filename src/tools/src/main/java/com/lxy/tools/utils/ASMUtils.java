package com.lxy.tools.utils;

import java.lang.reflect.Method;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

public class ASMUtils {
	/**
	 * 返回一个函数的 description
	 * @param returnType 函数返回值
	 * @param classes 函数形参表
	 * @return
	 */
	public static String createDesc(Class<?> returnType,Class<?> ... classes){
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		if(classes != null){
			for(Class<?> clazz:classes){
				sb.append(Type.getDescriptor(clazz));
			}
		}
		sb.append(")").append(returnType==null?"V": Type.getDescriptor(returnType));
		
		return sb.toString();
	}
	
	public static String createDesc(Method method){
		StringBuilder sb = new StringBuilder();
		Class<?> returnType = method.getReturnType();
		Class<?>[] classes = method.getParameterTypes();
		sb.append("(");
		if(classes != null){
			for(Class<?> clazz:classes){
				sb.append(Type.getDescriptor(clazz));
			}
		}
		sb.append(")").append(returnType==null?"V":Type.getDescriptor(returnType));
		
		return sb.toString();
	}
	
	/**
	 * 	 *  给一个ClassVisitor的一个属性附上值
	 * @param mv
	 * @param fieldName
	 * @param place 放在函数参数表第几个位置
	 * @param clazz 属性类型
	 * @param paras 属性构造函数所需参数 //这个还没有做
	 * @return
	 */
	public boolean addFieldValue(MethodVisitor mv,String fieldName,Integer place,Class<?> clazz,Object ... paras){
		String clazzInternalName = clazz.getName().replace(".", "/");
		mv.visitTypeInsn(Opcodes.NEW, clazzInternalName);
		mv.visitInsn(Opcodes.DUP);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, clazzInternalName, "<init>", "()V");
		mv.visitVarInsn(Opcodes.ASTORE, 1);
		
		return true;
	}
}
