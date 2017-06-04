package com.sleep.shop.util;

import java.util.UUID;

/*
 * 生成随机字符串
 */
public class UUIDUtils {
	public static String getUUIT(){
		return UUID.randomUUID().toString().replace("-", "");//去掉随机字符串中的-
	}
}
