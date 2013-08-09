package com.lxy.tools.NonReflectProxy.commons;

public class MethodDefine {
	private String name;
	private Object[] paras;
	private int opcode;
	private String desc;

	public MethodDefine() {

	}

	public MethodDefine(int opcode, String name, String desc, Object... paras) {
		this.opcode = opcode;
		this.name = name;
		this.paras = paras;
		this.desc = desc;
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

}
