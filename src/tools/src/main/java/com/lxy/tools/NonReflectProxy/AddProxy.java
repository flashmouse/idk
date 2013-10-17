package com.lxy.tools.NonReflectProxy;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.lxy.tools.NonReflectProxy.ClassOperator.CreateChildrenClassAdapter;
import com.lxy.tools.NonReflectProxy.ClassOperator.ExecuteSuperClassMethodAdapter;
import com.lxy.tools.NonReflectProxy.ClassOperator.FindMethodClassAdapter;
import com.lxy.tools.NonReflectProxy.commons.MethodProxyDefine;
import com.lxy.tools.utils.ASMUtils;
import com.lxy.tools.utils.ByteArrayClassLoader;
import com.lxy.tools.utils.Pair;

/**
 * 给所有找到的需要加上代理的类做加代理操作
 * @author lxy
 *
 */
public class AddProxy {
	private Map<Class<?>,List<MethodProxyDefine>> methods;
	private Map<Class<?>,Pair<ClassReader,ClassWriter>> classOps;
	private ByteArrayClassLoader clazzLoader;

	public AddProxy() {
		init();
	}

	public AddProxy( Map<Class<?>,List<MethodProxyDefine>> methods) {
		this.methods = methods;
		init();
	}
	
	private void init(){
		classOps = new HashMap<Class<?>,Pair<ClassReader,ClassWriter>>();
		clazzLoader = NonReflectProxyFactory.cl;
	}

	public void generateCode() throws Exception {
		if (!validateInfo()) {
			throw new Exception("wrong");
		}

		Iterator<Entry<Class<?>,List<MethodProxyDefine>>> it = methods.entrySet().iterator();
		Entry<Class<?>,List<MethodProxyDefine>> entry = null;
		Class<?> clazz = null;
		List<MethodProxyDefine> mpd = null;
		while(it.hasNext()){
			/*
			 * 创建一个类 继承自原有类
			 * 增加 构造函数 有代理的函数
			 * 给函数写上一行代码 super.XX();
			 * 添加代理对象到类中
			 * 修改构造函数 把代理对象的初始化方法放进去
			 */
			entry = it.next();
			clazz = entry.getKey();
			mpd = entry.getValue();
			ClassWriter cw1 = ASMUtils.createClass(clazz.getName().replace(".", "/")+"$proxy", clazz.getName().replace(".", "/"));

			ClassReader cr = new ClassReader(cw1.toByteArray());
			ClassWriter cw =new ClassWriter(ClassWriter.COMPUTE_FRAMES);
			cr.accept(new CreateChildrenClassAdapter(cw,mpd ,clazz.getName().replace(".", "/"),clazz.getName().replace(".", "/")+"$proxy"), ClassReader.SKIP_DEBUG);
			
			cw1 = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
			ClassAdapter classAdapter = new FindMethodClassAdapter(cw1, mpd,clazz.getName().replace(".", "/")+"$proxy");
			cr = new ClassReader(cw.toByteArray());
			cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
			
			byte[] data = cw1.toByteArray();
			File file = new File("D:\\workspace\\Test\\bin\\pack\\ExampleCode$proxy.class");
			FileOutputStream l = new FileOutputStream(file);
			l.write(data);
			l.close();
			String proxyClassName = clazz.getName()+"$proxy";
			Class<?> proxyClazz = clazzLoader.defineClassFromByteArray(proxyClassName, data);
		}
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
