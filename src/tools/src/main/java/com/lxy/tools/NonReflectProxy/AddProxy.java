package com.lxy.tools.NonReflectProxy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.lxy.tools.NonReflectProxy.commons.MethodDefine;
import com.lxy.tools.NonReflectProxy.commons.MethodProxyDefine;
import com.lxy.tools.NonReflectProxy.newCode.ICode;
import com.lxy.tools.utils.Pair;

/**
 * 给所有找到的需要加上代理的类做加代理操作
 * @author lxy
 *
 */
public class AddProxy {
	private Map<Class<?>,List<MethodProxyDefine>> methods;
	private Map<Class<?>,Pair<ClassReader,ClassWriter>> classOps;

	public AddProxy() {
		init();
	}

	public AddProxy( Map<Class<?>,List<MethodProxyDefine>> methods) {
		this.methods = methods;
		init();
	}
	
	private void init(){
		classOps = new HashMap<Class<?>,Pair<ClassReader,ClassWriter>>();
	}

	@SuppressWarnings("rawtypes")
	public void generateCode() throws Exception {
		if (!validateInfo()) {
			throw new Exception("wrong");
		}

		Iterator<Entry<Class<?>,List<MethodProxyDefine>>> it = methods.entrySet().iterator();
		Entry<Class<?>,List<MethodProxyDefine>> entry = null;
		Class<?> clazz = null;
		List<MethodProxyDefine> mpd = null;
		Pair<Class<? extends ICode>, Class<? extends ICode>> pair = null;
		while(it.hasNext()){
			entry = it.next();
			clazz = entry.getKey();
			mpd = entry.getValue();
		}
		
//		cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		
	}

	private boolean validateInfo() {
		if (methods == null) {
			return false;
		}

		// check whether has targetCode
		// ICode<?> now = null;
		// now = codeList;
		// boolean flag = true;
		// while (!(now instanceof TargetCode)) {
		// now = now.getNext();
		// if (now == null) {
		// flag = false;
		// }
		// }

		return true;
	}

}
