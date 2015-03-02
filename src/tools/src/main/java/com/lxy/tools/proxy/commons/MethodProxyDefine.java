package com.lxy.tools.proxy.commons;

import com.lxy.tools.proxy.newCode.ICode;
import com.lxy.tools.utils.Pair;

@SuppressWarnings("rawtypes")
public class MethodProxyDefine {
	private MethodDefine methodDefine;

	private Pair<Class<? extends ICode>, Class<? extends ICode>> proxies;

	public MethodProxyDefine(MethodDefine methodDefine,
			Pair<Class<? extends ICode>, Class<? extends ICode>> proxies) {
		this.methodDefine = methodDefine;
		this.proxies = proxies;
	}

	public MethodDefine getMethodDefine() {
		return methodDefine;
	}

	public void setMethodDefine(MethodDefine methodDefine) {
		this.methodDefine = methodDefine;
	}

	public Pair<Class<? extends ICode>, Class<? extends ICode>> getProxies() {
		return proxies;
	}

	public void setProxies(
			Pair<Class<? extends ICode>, Class<? extends ICode>> proxies) {
		this.proxies = proxies;
	}

}
