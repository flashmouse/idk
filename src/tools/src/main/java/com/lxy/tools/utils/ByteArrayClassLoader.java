package com.lxy.tools.utils;

public class ByteArrayClassLoader extends ClassLoader {
	public Class defineClassFromByteArray(String className, byte[] classFile)
			throws ClassFormatError {
		return defineClass(className, classFile, 0, classFile.length);
	}
	

	public ByteArrayClassLoader(){
		super();
	}
	
	public ByteArrayClassLoader(ClassLoader cl){
		super(cl);
	}
}
