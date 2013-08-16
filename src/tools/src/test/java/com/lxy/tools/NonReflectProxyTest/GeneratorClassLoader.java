package com.lxy.tools.NonReflectProxyTest;

public class GeneratorClassLoader extends ClassLoader {

	@SuppressWarnings("rawtypes")
	public Class defineClassFromClassFile(String className, byte[] classFile)
			throws ClassFormatError {
		return defineClass(className, classFile, 0, classFile.length);
	}
}