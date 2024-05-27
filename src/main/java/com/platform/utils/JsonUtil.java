package com.platform.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON 工具类
 * 
 * @author baoyw
 *
 * @version 1.0
 *
 * 创建时间:2016年8月4日下午5:20:40
 */
public class JsonUtil {

    /**
     * 生成ComboTreeJSON
     * 
     * @param list
     * @param colId
     * @param colName
     * @return
     */
    public static String createComboTree(List list, String colId, String colName) {
        JSONArray json = new JSONArray();
        if (list.size() <= 0) {
            JSONObject obj = new JSONObject();
            obj.put("id", "1");
            obj.put("text", "查询列表");
            json.add(obj);
        } else {
            JSONObject obj = new JSONObject();
            obj.put("id", "1");
            obj.put("text", "查询列表");
            obj.put("children", createComboTreeChildren(list, colId, colName));
            json.add(obj);
        }
        return json.toString();
    }

    /**
     * 子节点
     * 
     * @param list
     * @param colId
     * @param colName
     * @return
     */
    private static JSONArray createComboTreeChildren(List<Object> list, String colId, String colName) {
        JSONArray json = new JSONArray();
        for (int index = 0; index < list.size(); index++) {
            List<Map<String, Object>> fields = getFiledsInfo(list.get(index));
            JSONObject obj = new JSONObject();
            for (int i = 0; i < fields.size(); i++) {
                Object attribute = fields.get(i).get("name");
                if (attribute.equals("id")) {
                    obj.put("id", fields.get(i).get("value"));
                } else if (attribute.equals("name")) {
                    obj.put("text", fields.get(i).get("value"));
                }
            }
            json.add(obj);
        }
        return json;
    }

    /**
     * 对象属性名集合
     * 
     * @param o
     * @return
     */
    private static List<Map<String, Object>> getFiledsInfo(Object o) {
        Class<?> clazz = o.getClass();
        clazz = clazz.getSuperclass();
        Field[] fields = clazz.getDeclaredFields();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> infoMap = null;
        for (int i = 0; i < fields.length; i++) {
            infoMap = new HashMap<String, Object>();
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 获取对象属性值集合
     * 
     * @param fieldName
     * @param o
     * @return
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
