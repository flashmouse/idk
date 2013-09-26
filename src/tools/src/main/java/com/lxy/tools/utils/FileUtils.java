package com.lxy.tools.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import com.lxy.tools.utils.afterOperate.AfterOperate;

/**
 * 
 * @author lxy
 *
 */
public class FileUtils {
	/**
	 * 获取指定目录下的所有的文件，
	 * 返回值是这个文件在指定目录下的相对路径及其文件名
	 * @param 查询路径
	 * @param filter
	 * @return 这个文件在指定目录下的相对路径及其文件名
	 * @throws FileNotFoundException
	 * 
	 */
	public static List<String> getClassesWholeName(String path,FilenameFilter filter,AfterOperate<String,String> afterOperate ) throws FileNotFoundException{
		File file = new File(path);
		if(!isDirectory(file)  ){
			throw new FileNotFoundException();
		}
		List<Pair<String,File>> tmpDirs = null;
		List<Pair<String,File>> dirs = new ArrayList<Pair<String,File>>();
		dirs.add(new Pair<String,File>("",file));
		List<Pair<String,File>> files = new ArrayList<Pair<String,File>>();
		while(dirs != null && dirs.size()>0){
			tmpDirs = new ArrayList<Pair<String,File>>();
			for(Pair<String,File> pair:dirs){
				tmpDirs.addAll(addClassNames(pair, files, filter));
			}
			dirs = tmpDirs;
		}
		
		List<String> re = new ArrayList<String>();
		
		if(afterOperate == null){
			for(Pair<String,File> pair:files){
				re.add(pair.getFirst()+File.separator+pair.getSecond().getName());
			}
		}else{
			for(Pair<String,File> pair:files){
				re.add(afterOperate.operate(pair.getFirst()+File.separator+pair.getSecond().getName()));
			}
		}
		
		return re;
	}
	
	private static List<Pair<String,File>> addClassNames(Pair<String,File> pair, List<Pair<String,File>> files,FilenameFilter filter) {
		List<Pair<String,File>> reDirs = new ArrayList<Pair<String,File>>();
		File dir = null;
		String sTmp = null;
		sTmp = pair.getFirst();
		dir = pair.getSecond();
		
		File[] lists = dir.listFiles(filter);
		for(File file:lists){
			if(file.isDirectory()){
				reDirs.add(new Pair<String,File>(sTmp+File.separator+dir.getName(),file));
			}else{
				files.add(new Pair<String,File>(sTmp+File.separator+dir.getName(),file));
			}
		}
		
		return reDirs;
	}

	public static boolean isDirectory (File file){
		return (file.exists() && file.isDirectory() );
	}
	
	public static boolean isFile(File file){
		return (file.exists() && file.isFile() );
	}
}
