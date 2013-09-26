package com.lxy.tools.utils.afterOperate;

public abstract class AfterOperate<Ori,Re> {
	private Object paras;
	
	public abstract Re operate(Ori value);
	
	public AfterOperate(){
		
	}
	
	public AfterOperate(Object paras ){
		this.paras=paras;
	}
	
	public Object getParas() {
		return paras;
	}
	
	public void setParas(Object paras) {
		this.paras = paras;
	}
	
	
}
