package com.lxy.tools.NonReflectProxy;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;

import com.lxy.tools.NonReflectProxy.ClassOperator.FindMethodClassAdapter;
import com.lxy.tools.NonReflectProxy.commons.MethodProxyDefine;
import com.lxy.tools.NonReflectProxy.example.ExampleProxied;
import com.lxy.tools.NonReflectProxy.newCode.ICode;
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
		clazzLoader = new ByteArrayClassLoader(AddProxy.class.getClassLoader());
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
			entry = it.next();
			clazz = entry.getKey();
			mpd = entry.getValue();
			
			ClassWriter cw =new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ClassReader cr = new ClassReader(clazz.getName());
			ClassAdapter classAdapter = new FindMethodClassAdapter(cw, mpd,clazz.getName());
			cr.accept(classAdapter, ClassReader.EXPAND_FRAMES);
			
			byte[] data = cw.toByteArray();
			File file = new File("D:\\workspace\\Test\\bin\\pack\\ExampleCode$proxy.class");
			FileOutputStream l = new FileOutputStream(file);
			l.write(data);
			l.close();
			String proxyClassName = clazz.getName()+"$proxy";
			Class<?> proxyClazz = clazzLoader.defineClassFromByteArray(proxyClassName, data);
			Field[] fs = proxyClazz.getDeclaredFields();
			Object value =  proxyClazz.newInstance();
			Method[] methods = proxyClazz.getDeclaredMethods();
			for(Method m:methods){
				m.invoke(value, null);
				System.out.println("-------------------");
			}
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
