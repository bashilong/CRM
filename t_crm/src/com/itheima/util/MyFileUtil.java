package com.itheima.util;

import java.util.UUID;

public class MyFileUtil {
	/**
	 * 获取文件的UUID名称
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName){
		String prefix = UUID.randomUUID().toString().replaceAll("-", "");
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		return prefix+suffix;
	}
}
