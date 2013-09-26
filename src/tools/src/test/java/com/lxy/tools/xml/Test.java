package com.lxy.tools.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.lxy.tools.NonReflectProxy.configure.NonReflectProxyConfFile;
import com.lxy.tools.utils.xmlUtils.JAXBUtils;

public class Test {
	public static void main(String [] args) throws JAXBException{
		NonReflectProxyConfFile file = new NonReflectProxyConfFile();
		List<String> a = new ArrayList<String>();
		a.add("hhe");
		a.add("QAQ");
		file.setScanPackage(a);
		JAXBUtils.marshal(NonReflectProxyConfFile.class, file,System.out);
	}
}
