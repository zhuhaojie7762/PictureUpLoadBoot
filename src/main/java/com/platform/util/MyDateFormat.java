package com.platform.util;


import com.platform.utils.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;


/**
 * 全局类型转换器
 *
 * @Author:zhuhaojie
 * @Date:12:04 2018/12/18
 */
public class MyDateFormat implements Converter<String,Date> {
    @Override
    public Date convert(String source) {
        Date date;
        source = source.trim();
        date =  DateUtils.strToDate(source, "yyyy-MM-dd HH:mm:ss");
        if (date == null) {
            date = DateUtils.strToDate(source, "yyyy-MM-dd");
        }
        if (date == null) {
            date = DateUtils.strToDate(source, "yyyyMMdd");
        }
        if (date == null) {
            date = DateUtils.strToDate(source, "yyyyMMdd HH:mm:ss");
        }
        return date;
    }
}
