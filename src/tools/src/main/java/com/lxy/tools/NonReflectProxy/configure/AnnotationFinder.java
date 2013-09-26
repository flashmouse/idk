package com.lxy.tools.NonReflectProxy.configure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;

import com.lxy.tools.NonReflectProxy.annotation.Proxied;
import com.lxy.tools.NonReflectProxy.commons.MethodDefine;
import com.lxy.tools.NonReflectProxy.commons.MethodProxyDefine;
import com.lxy.tools.NonReflectProxy.newCode.ICode;
import com.lxy.tools.utils.Pair;
import com.lxy.tools.utils.packageUtil.PackageFilter;
import com.lxy.tools.utils.packageUtil.PackageUtils;
import com.lxy.tools.utils.xmlUtils.JAXBUtils;

public class AnnotationFinder {
	public NonReflectProxyConfFile getConfFile() throws FileNotFoundException,
			JAXBException {
		return getConfFile("resources/nrp.xml");
	}

	public NonReflectProxyConfFile getConfFile(String fileName)
			throws FileNotFoundException, JAXBException {
		File file = new File(fileName);
		if (!(file.exists() && file.isFile())) {
			throw new FileNotFoundException(file.getAbsoluteFile()
					.getAbsolutePath());
		}

		FileInputStream stream = new FileInputStream(file);
		return (NonReflectProxyConfFile) JAXBUtils.unmarshal(
				NonReflectProxyConfFile.class, stream);
	}

	public Set<Class<?>> getNeedReflectedClasses()
			throws FileNotFoundException, JAXBException {
		return getNeedReflectedClasses("resources/nrp.xml");
	}

	public Set<Class<?>> getNeedReflectedClasses(String xml)
			throws FileNotFoundException, JAXBException {
		NonReflectProxyConfFile file = getConfFile(xml);
		List<String> packages = file.getScanPackage();

		Set<Class<?>> classes = new HashSet<Class<?>>();
		PackageFilter filter = new ProxiedAnnotationFilter();
		if (packages != null) {
			for (String pack : packages) {
				classes.addAll(PackageUtils.getClassesWithoutLoad(pack, filter));
			}
		}

		return classes;
	}

	private Map<Class<?>, List<MethodProxyDefine>> createNeedProxiedMethodsMetaData(
			Set<Class<?>> classes) {
		// Map<MethodDefine, Pair<Class<? extends ICode>, Class<? extends
		// ICode>>> result = new HashMap<MethodDefine, Pair<Class<? extends
		// ICode>, Class<? extends ICode>>>();
		Map<Class<?>, List<MethodProxyDefine>> result = new HashMap<Class<?>, List<MethodProxyDefine>>();
		for (Class<?> clazz : classes) {
			result.put(clazz,createOneClassNeedProxiedMethodsMetaData(clazz));
		}

		return result;
	}

	@SuppressWarnings("rawtypes")
	private  List<MethodProxyDefine> createOneClassNeedProxiedMethodsMetaData(
			Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		Proxied classProxied = clazz.getAnnotation(Proxied.class);
		Proxied methodProxied = null;

		List<MethodProxyDefine> lists = new ArrayList<MethodProxyDefine>();
		for (Method method : methods) {
			methodProxied = method.getAnnotation(Proxied.class);
			if (methodProxied != null) {
				lists.add(new MethodProxyDefine(
						new MethodDefine(clazz, method.getName(), method
								.getParameterTypes()),
						new Pair<Class<? extends ICode>, Class<? extends ICode>>(
								methodProxied.BeforeProxy(), methodProxied
										.AfterProxy())));
			} else if (classProxied != null) {
				lists.add(new MethodProxyDefine(
						new MethodDefine(clazz, method.getName(), method
								.getParameterTypes()),
						new Pair<Class<? extends ICode>, Class<? extends ICode>>(
								classProxied.BeforeProxy(), classProxied
										.AfterProxy())));
			}
		}

		return lists;
	}

	public Map<Class<?>, List<MethodProxyDefine>> init()
			throws FileNotFoundException, JAXBException {
		return createNeedProxiedMethodsMetaData(getNeedReflectedClasses());
	}

	// public static void main(String[] args) throws FileNotFoundException,
	// JAXBException {
	// AnnotationFinder finder = new AnnotationFinder();
	// Map<MethodDefine, Pair<Class<? extends ICode>, Class<? extends ICode>>>
	// values = finder
	// .init();
	// Iterator<Entry<MethodDefine, Pair<Class<? extends ICode>, Class<? extends
	// ICode>>>> it = values
	// .entrySet().iterator();
	// Entry<MethodDefine, Pair<Class<? extends ICode>, Class<? extends ICode>>>
	// entry = null;
	// MethodDefine define = null;
	// Pair<Class<? extends ICode>, Class<? extends ICode>> pair = null;
	// while (it.hasNext()) {
	// entry = it.next();
	// define = entry.getKey();
	// pair = entry.getValue();
	// System.out.println("class:" + define.getClazz().getName()
	// + "\nname:" + define.getName() + "\nbefore:"
	// + pair.getFirst().getName() + "\nafter:"
	// + pair.getSecond().getName()+"\n");
	// }
	// }
}
