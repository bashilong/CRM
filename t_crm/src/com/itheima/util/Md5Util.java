package com.itheima.util;

import org.springframework.util.DigestUtils;

public class Md5Util {
	public static String encodePwd(String pwd){
		for (int i = 0; i < 10; i++) {
			pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
		}
		return pwd;
	}
}
