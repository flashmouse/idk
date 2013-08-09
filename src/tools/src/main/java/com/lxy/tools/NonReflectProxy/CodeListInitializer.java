package com.lxy.tools.NonReflectProxy;

import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.lxy.tools.NonReflectProxy.commons.MethodDefine;
import com.lxy.tools.NonReflectProxy.newCode.ICode;
import com.lxy.tools.commons.MyStringUtils;
import com.lxy.tools.commons.Pair;

public class CodeListInitializer {
	private String className;
	private List<Pair<MethodDefine, ICode<?>>> methods;

	private ClassReader cr;
	private ClassWriter cw;

	public CodeListInitializer() {

	}

	public CodeListInitializer(ICode<?> codeList, String cn,
			List<Pair<MethodDefine, ICode<?>>> methods) {
		this.className = cn;
		this.methods = methods;
	}

	public void generateCode() throws Exception {
		if (!validateInfo()) {
			throw new Exception("wrong");
		}

		cr = new ClassReader(className);
		cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

	}

	private boolean validateInfo() {
		if (MyStringUtils.isEmpty(className)) {
			return false;
		}

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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Pair<MethodDefine, ICode<?>>> getMethods() {
		return methods;
	}

	public void setMethods(List<Pair<MethodDefine, ICode<?>>> methods) {
		this.methods = methods;
	}

}
