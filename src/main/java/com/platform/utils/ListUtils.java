package com.platform.utils;

import java.io.*;
import java.util.*;

/**
 * 类名称：ListUtils 类描述： 集合操作工具类 创建人：zhuhaojie 创建时间：2016年12月5日 上午11:06:01
 */
public class ListUtils {

    /**
     * 移除集合中的空字符串及null
     *
     * @param list 原始集合对象
     * @return List<String> 处理完成的集合对象
     * @author zhuhaojie
     * @time 2016年11月23日上午10:48:05
     */
    public static List<String> removeNullAndEmpty(List<String> list) {

        if (list == null) {
            return null;
        }
        int size = list.size();
        if (size == 0) {
            return list;
        }
        List<String> result = new ArrayList<String>();
        for (String s : list) {
            if (s != null) {
                s = s.trim();
                if (!"".equals(s)) {
                    result.add(s);
                }
            }
        }
        return result;
    }

    /**
     * 移除集合中的null和空字符串
     *
     * @param collection 需要操作的集合类
     * @return Collection 集合类的根接口对象
     * @author zhuhaojie
     * @time 2016年12月31日 下午8:42:47
     */
    public static Collection<?> removeNullValue(Collection<?> collection) {
        if (collection == null) {
            return null;
        }
        Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            if (null == o || "".equals(o + "")) {
                iterator.remove();
            }
        }
        return collection;
    }

    /**
     * 集合类 序列化方式的深度复制
     *
     * @param src 要拷贝的源集合
     * @param <T> 任意实体对象
     * @return List<T> 拷贝完成的新集合
     * @throws IOException            当拷贝失败时可能抛出的异常
     * @throws ClassNotFoundException 当拷贝失败时可能抛出的异常
     * @author：zhuhaojie
     * @time：2017年2月27日 下午5:01:05
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        if (src == null) {
            return null;
        }
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    /**
     * 去除List中的重复元素
     *
     * @param src 源集合
     * @param <T> 任意合法对象
     * @return List<T> 去除重复元素后的集合
     * @author：zhuhaojie
     * @time：2017年7月27日 上午9:55:27
     */
    public static <T> List<T> removeRepeatElement(List<T> src) {
        if (src == null) {
            return null;
        } else if (src.size() < 2) {
            return src;
        }
        Set<T> set = new HashSet<T>();
        for (T t : src) {
            set.add(t);
        }
        List<T> list = new ArrayList<T>();
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }

    /**
     * 将集合转换成字符串
     *
     * @param list 源数据
     * @return 转换拼接后的字符串
     * @author：zhuhaojie
     * @time：2017年8月1日 上午10:18:48
     */
    public static String converListToString(List<String> list) {
        if (list == null) {
            return null;
        } else if (list.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : list) {
            stringBuffer.append(s + ",");
        }
        String result = stringBuffer.toString();
        result = result.trim();
        result = result.substring(0, result.length() - 1);
        return result;
    }

    /**
     * 两个集合元素的差集
     *
     * @param first  第一个集合
     * @param second 第二个集合
     * @author：zhuhaojie
     * @time：2017年10月31日 下午12:48:33
     */
    public static <T> List<T> differElement(List<T> first, List<T> second) {

        if (first == null && second == null) {
            return null;
        }
        if (first == null && second != null) {
            return second;
        }
        if (first != null && second == null) {
            return first;
        }
        //两个都不是null
        List<T> result = new ArrayList<T>();
        for (T obj : first) {
            if (!second.contains(obj)) {
                result.add(obj);
            }
        }
        for (T obj : second) {
            if (!first.contains(obj)) {
                result.add(obj);
            }
        }
        return result;
    }


    /**
     * 将一组数据平均分成n组
     *
     * @param source 要分组的数据源
     * @param n      平均分成n组
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<>();
        int remainder = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value;
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }


    /**
     * 将一组数据固定分组，每组n个元素
     *
     * @param source 要分组的数据源
     * @param n      每组n个元素
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> fixedGrouping(List<T> source, int n) {

        if (null == source || source.size() == 0 || n <= 0)
            return null;
        List<List<T>> result = new ArrayList<>();
        int sourceSize = source.size();
        int size = (source.size() / n) + 1;
        for (int i = 0; i < size; i++) {
            List<T> subset = new ArrayList<>();
            for (int j = i * n; j < (i + 1) * n; j++) {
                if (j < sourceSize) {
                    subset.add(source.get(j));
                }
            }
            result.add(subset);
        }
        return result;
    }


    /**
     * 将一组数据固定分组，每组n个元素
     *
     * @param source 要分组的数据源
     * @param n      每组n个元素
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> fixedGrouping2(List<T> source, int n) {

        if (null == source || source.size() == 0 || n <= 0)
            return null;
        List<List<T>> result = new ArrayList<>();
        int remainder = source.size() % n;
        int size = (source.size() / n);
        for (int i = 0; i < size; i++) {
            List<T> subset;
            subset = source.subList(i * n, (i + 1) * n);
            result.add(subset);
        }
        if (remainder > 0) {
            List<T> subset;
            subset = source.subList(size * n, size * n + remainder);
            result.add(subset);
        }
        return result;
    }
}
