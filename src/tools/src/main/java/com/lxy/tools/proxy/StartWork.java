package com.lxy.tools.proxy;

import java.util.List;
import java.util.Map;

import com.lxy.tools.proxy.commons.MethodProxyDefine;
import com.lxy.tools.proxy.configure.AnnotationFinder;

/**
 * 整个项目的入口
 * @author lxy
 *
 */
public class StartWork {
	private static StartWork instance = new StartWork();
	
	private StartWork(){
		
	}
	
	public static StartWork getInstance(){
		return instance;
	}
	
	public void execute() throws Exception{
		AnnotationFinder finder = new AnnotationFinder();
		Map<Class<?>,List<MethodProxyDefine>> proxiedMthods = finder.init();
		AddProxy addProxy = new AddProxy(proxiedMthods);
		addProxy.generateCode();
	}
}
