package com.lxy.tools.NonReflectProxy.commons;

public class MethodDefine {
	private Class<?> clazz;
	private String name;
	private Object[] paras;
	private int opcode;
	private String desc;

	public MethodDefine() {

	}

	public MethodDefine(Class<?> clazz , String name,  Object... paras) {
		this.clazz=clazz;
		this.name=name;
		this.paras=paras;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object[] getParas() {
		return paras;
	}

	public void setParas(Object[] paras) {
		this.paras = paras;
	}

	public int getOpcode() {
		return opcode;
	}

	public void setOpcode(int opcode) {
		this.opcode = opcode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

}
