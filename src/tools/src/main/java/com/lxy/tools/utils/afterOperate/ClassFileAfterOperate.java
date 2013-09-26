package com.lxy.tools.utils.afterOperate;

import java.io.File;

import com.lxy.tools.utils.MyStringUtils;

public class ClassFileAfterOperate extends AfterOperate<String, String> {

	public String operate(String value) {
		if(MyStringUtils.isEmpty(value)){
			return null;
		}
		String tmp = String.valueOf(getParas());
		return (tmp.substring(0,tmp.lastIndexOf("."))+value.substring(0,value.lastIndexOf("."))).replace(File.separator, ".");
	}
	
	public ClassFileAfterOperate(){
		super();
	}
	
	public ClassFileAfterOperate(String packPre){
		super((Object ) packPre);
	}

}
