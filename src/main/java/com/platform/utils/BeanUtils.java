package com.platform.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author Administrator
 *
 */
public class BeanUtils {

	/**
	 * 日期格式化
	 */
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 将bean转化为字符串，查看bean内容
	 * @param bean 要转化的bean
	 * @return String
	 */
	public static String getString(Object bean) {
		StringBuffer sb = new StringBuffer();
		if(bean==null){
			sb.append("BeanToString[]");
			return sb.toString();
		}
		
		sb.append("BeanToString[");
		Field[] farr = bean.getClass().getDeclaredFields();
		for (Field field : farr) {
			try {
				field.setAccessible(true);
				sb.append(field.getName());
				sb.append("=");
				if (field.get(bean) instanceof Date) {
					// 日期的处理
					sb.append(sdf.format(field.get(bean)));
				} else {
					sb.append(field.get(bean));
				}
				sb.append("|");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
