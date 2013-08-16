package com.lxy.tools.NonReflectProxyTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.lxy.tools.NonReflectProxy.ClassOperator.FindMethodClassAdapter;

public class NonReflectProxyTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void test() throws IOException, InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		ClassReader cr = new ClassReader("com.lxy.tools.NonReflectProxyTest.Hello");
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		
		ClassAdapter classAdapter = new TestMethodAdapter(cw);
//		classAdapter
		cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
		
		byte[] data = cw.toByteArray();
		String path = this.getClass().getResource("").toString();
		path = path.substring(5);
		File file = new File(path+"Hello.class");
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(data);
		fout.close();
		
		Hello hello = new Hello();
		hello.getClass().getMethod("begin").invoke(hello);
		hello.test();
	}

}
