package com.sleep.shop.util;

import java.util.UUID;

/*
 * ��������ַ���
 */
public class UUIDUtils {
	public static String getUUIT(){
		return UUID.randomUUID().toString().replace("-", "");//ȥ������ַ����е�-
	}
}
