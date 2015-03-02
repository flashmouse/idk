package com.lxy.tools.proxy.commons;

public class MethodDefine {
    private Class<?> clazz;
    private String name;
    private Class<?>[] paras;
    private int opcode;
    private String desc;

    public MethodDefine() {

    }

    public MethodDefine(Class<?> clazz, String name, Class<?>[] paras, String desc) {
        this.clazz = clazz;
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

    public Class<?>[] getParas() {
        return paras;
    }

    public void setParas(Class<?>[] paras) {
        this.paras = paras;
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
