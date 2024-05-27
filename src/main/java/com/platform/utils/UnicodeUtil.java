package com.platform.utils;
/** 
 *<p>
 *	UnicodeUtil Unicode工具提供对中文编码与解码操作。
 *<p>
 * @author baoyw 
 *
 * @version 1.0
 *
 * 创建时间：2017年1月11日 下午7:38:33 
 *  
 */
public class UnicodeUtil {

	/**
	 * 编码
	 * 
	 * @param cn 中文汉字
	 * 
	 * @return unicode字符串
	 */
	public static String cnToUnicode(String cn) {
		char[] chars = cn.toCharArray();
		String returnStr = "";
		for (int i = 0; i < chars.length; i++) {
			returnStr += "\\u" + Integer.toString(chars[i], 16);
		}
		return returnStr;
	}

	/**
	 * 解码
	 * 
	 * @param unicode unicode字符串
	 * 
	 * @return 中文汉字
	 */
	public static String unicodeToCn(String unicode) {
		String[] strs = unicode.split("\\\\u");
		String returnStr = "";
		for (int i = 1; i < strs.length; i++) {
			returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
		}
		return returnStr;
	}
}
