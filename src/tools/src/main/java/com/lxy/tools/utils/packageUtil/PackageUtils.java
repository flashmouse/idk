package com.lxy.tools.utils.packageUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.lxy.tools.utils.ALLUtils;
import com.lxy.tools.utils.FileUtils;
import com.lxy.tools.utils.afterOperate.ClassFileAfterOperate;

public class PackageUtils {
	public static List<Class<?>> getClassesWithoutLoad(String packageName,PackageFilter ... filter) throws FileNotFoundException{
		String filePath = null;
		if(packageName.charAt(0)=='/'){
			filePath = packageName;
		}else{
			filePath = ALLUtils.getContextPath().getPath()+packageName.replace(".", File.separator);
		}
		List<String> names = FileUtils.getClassesWholeName(filePath, null,new ClassFileAfterOperate(packageName));
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for(String name:names){
			try {
				Class<?> tmp = Class.forName(name,false,PackageUtils.class.getClassLoader());
				classes.add(tmp);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		if(filter != null && classes.size()>0){
			getLeftFile(classes,filter);
		}
		
		return classes;
	}
	
	private static List<Class<?>> getLeftFile(List<Class<?>> lists,PackageFilter ... filters){
		if(filters == null){
			return lists;
		}
		Class<?> list = null;
		int i=0;
		boolean flag = true;
		while(i<lists.size() && lists.size()>0){
			list = lists.get(i);
			flag = true;
			for(PackageFilter filter:filters){
				if(!filter.leave(list)){
					lists.remove(i);
					flag = false;
					break;
				}
			}
			
			if(flag){
				i++;
			}
		}
		
		return lists;
	}
	
	@Deprecated
	public static List<Class<?>> getClasses(String packageName,PackageFilter ... filter){
		Package pack = Package.getPackage(packageName);
		
		return null;
	}
	
	/**
	 * 把包括包名在内的字符串作为参数传入，仅返回一个class的名字
	 */
	public static String getClassNameWithOutPackage(String p){
		p = p.replace("/", ".");
		int place = p.lastIndexOf(".");
		if(place <0){
			return p;
		}
		
		return p.substring(place+1);
	}
}
