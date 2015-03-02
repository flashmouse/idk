package com.lxy.tools.proxy.configure;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="nonReflectProxy")
public class NonReflectProxyConfFile {
	private List<String> scanPackage;
	
	private List<String> className;

	public List<String> getScanPackage() {
		return scanPackage;
	}

	public void setScanPackage(List<String> scanPackage) {
		this.scanPackage = scanPackage;
	}

	public List<String> getClassName() {
		return className;
	}

	public void setClassName(List<String> className) {
		this.className = className;
	}
	
}
