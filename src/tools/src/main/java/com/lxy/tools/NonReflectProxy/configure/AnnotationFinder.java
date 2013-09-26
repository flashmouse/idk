package com.lxy.tools.NonReflectProxy.configure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import com.lxy.tools.utils.packageUtil.PackageFilter;
import com.lxy.tools.utils.packageUtil.PackageUtils;
import com.lxy.tools.utils.xmlUtils.JAXBUtils;

public class AnnotationFinder {
	public NonReflectProxyConfFile getConfFile() throws FileNotFoundException, JAXBException{
		return getConfFile("resources/nrp.xml");
	}
	
	public NonReflectProxyConfFile getConfFile(String fileName) throws FileNotFoundException, JAXBException{
		File file = new File(fileName);
		if(  !(file.exists() && file.isFile())){
			throw new FileNotFoundException(file.getAbsoluteFile().getAbsolutePath());
		}
		
		FileInputStream stream = new FileInputStream(file);
		return (NonReflectProxyConfFile) JAXBUtils.unmarshal(NonReflectProxyConfFile.class, stream);
	}
	
	public Set<Class<?>> getNeedReflectedClasses() throws FileNotFoundException, JAXBException{
		return getNeedReflectedClasses("resources/nrp.xml");
	}
	
	public Set<Class<?>> getNeedReflectedClasses(String xml) throws FileNotFoundException, JAXBException{
		NonReflectProxyConfFile file = getConfFile(xml); 
		List<String> packages = file.getScanPackage();
		
		Set<Class<?>> classes = new HashSet<Class<?>>();
		PackageFilter filter = new ProxiedAnnotationFilter();
		if(packages != null){
			for(String pack:packages){
				classes.addAll(PackageUtils.getClassesWithoutLoad(pack,filter));
			}
		}
		
		return classes;
	}
	
	public static void main(String [] args) throws FileNotFoundException, JAXBException{
		AnnotationFinder finder = new AnnotationFinder();
		Set<Class<?>> classes = finder.getNeedReflectedClasses();
		for(Class<?> clazz :classes ){
			System.out.println(clazz.getName());
		}
	}
}
