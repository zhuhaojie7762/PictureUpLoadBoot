package com.platform.utils;

import java.security.MessageDigest;
/**
 * MD5加密
 * @author Administrator
 *
 */
public class MD5 {
    /**
     * main 函数
     * @param args 参数
     */
	public static void main(String[] args) {
		System.out.println(md5("111111"));
	}
	/**
	 * 加密
	 * @param str 要加密字符串
	 * @return String 加密后的字符串
	 */
	public static String md5(String str) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] bytes = str.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);
			byte[] updateBytes = messageDigest.digest();
			int len = updateBytes.length;
			char[] myChar = new char[len * 2];
			int k = 0;
			for (int i = 0; i < len; i++) {
				byte byte0 = updateBytes[i];
				myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
				myChar[k++] = hexDigits[byte0 & 0x0f];
			}
			return new String(myChar);
		} catch (Exception e) {
			return null;
		}

	}
}
