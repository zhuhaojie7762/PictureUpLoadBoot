package com.platform.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * 类名称：StringUtil 类描述：字符串工具类 创建人：zhuhaojie 创建时间：2016年11月24日 上午9:45:20
 */
public class StringUtil {

    /**
     * 封闭无参构造方法
     * 
     * @author zhuhaojie
     * @time 2017年1月3日 下午6:29:37
     */
    private StringUtil() {

    }

    /**
     * 输入流编码进行base64编码
     * 
     * @param in
     *            输入流对象
     * @return String 字符串
     */
    public static String stringBase64(InputStream in) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(data); // 返回Base64编码过的字节数组字符串
    }

    /**
     * 可能抛出空指针异常 判断字符串str是否包含在数组 array中
     * 
     * @author zhuhaojie
     * @time 2016年11月23日下午4:38:49
     * @param array
     *            待检验数组
     * @param str
     *            待检验字符串
     * @return boolean
     * 
     */
    public static boolean hasElement(String[] array, String str) {
        str = str.trim();
        boolean t = false;
        for (int i = 0; i < array.length; i++) {
            if (str.equals(array[i])) {
                t = true;
                break;
            }
        }
        return t;
    }

    /**
     * 判断字符串是否不是null或者不是空字符串
     * 
     * @author zhuhaojie
     * @time 2017年1月9日 上午11:45:02
     * @param s
     *            受检查的字符串
     * @return boolean 如果不是null也不是空字符串返回true,否则返回false
     */
    public static boolean isNotEmpty(String s) {
        if (s == null) {
            return false;
        }
        s = s.trim();
        if ("".equals(s)) {
            return false;
        }
        return true;
    }
    
    
    
    /**
     * 
     * 
     * @param str  要切分的字符串
     * @param split 要按照此字符串或者字符进行切分
     * @author：zhuhaojie
     * @time：2017年3月29日 下午4:43:33
     * @return string[] 切分后的数组
     */
    public static  String[] splitStringBySplit(String str, String split) {
        if (str == null) {
            return null;
        }
        str = str.trim();
        if (str.equals("null") || str.equals("")) {
            return null;
        } else {
            int index = str.indexOf(split);
            if (index == -1) {
                return new String[]{str};
            } else {
                String[] taskTypesArray = str.split(",");
                if (taskTypesArray == null || taskTypesArray.length == 0) {
                    return null;
                } else {
                    return taskTypesArray;
                } 
            }
        }
    }

    public static String makeQueryStringAllRegExp(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }

        return str.replace("\\", "\\\\").replace("*", "\\*")
                .replace("+", "\\+").replace("|", "\\|")
                .replace("{", "\\{").replace("}", "\\}")
                .replace("(", "\\(").replace(")", "\\)")
                .replace("^", "\\^").replace("$", "\\$")
                .replace("[", "\\[").replace("]", "\\]")
                .replace("?", "\\?").replace(",", "\\,")
                .replace(".", "\\.").replace("&", "\\&");
    }

}