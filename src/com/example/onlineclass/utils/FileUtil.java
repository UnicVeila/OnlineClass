package com.example.onlineclass.utils;

import java.io.File;

/**
 * @author anumbrella
 * 
 * @date 2015-8-21 下午3:39:38
 * 
 *       文件处理类工具类
 * 
 */
public class FileUtil {

	/**
	 * 根据文件路径 递归创建文件
	 * 
	 * @param filePath
	 */
	public void createDipPath(String filePath) {

		String parentFilePath = filePath
				.substring(0, filePath.lastIndexOf("/"));
		File originalFile= new File(filePath);
		File parentFile = new File(parentFilePath);
		if (!originalFile.exists()) {
			parentFile.mkdirs();
		   try {
			   originalFile.createNewFile();
			} catch (Exception e) {
			// TODO: handle exception
		}
			
		}
		

	}

}
