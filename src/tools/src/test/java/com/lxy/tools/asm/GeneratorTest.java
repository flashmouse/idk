package com.lxy.tools.asm;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Test;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class GeneratorTest {
	public void run() throws Exception {
//		String path = GeneratorTest.class.getResource("").toString();
//		path = path.substring(5);
//
//		ClassReader cr = new ClassReader("com.lxy.tools.asm.Account");
//		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//		ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
//		cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
//		byte[] data = cw.toByteArray();
//		File file = new File(path + "Account.class");
//		FileOutputStream fout = new FileOutputStream(file);
//		fout.write(data);
//		fout.close();
//		Account account = new Account();
//		account.operation();
	}
}
