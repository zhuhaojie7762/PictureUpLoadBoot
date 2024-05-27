package com.platform.utils;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.*;

public class VelocityUtils extends ResourceLoader {
	public static String render(Object obj, String template) throws ParseErrorException{
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		Properties p = new Properties();
		p.put("resource.loader", "srl");
		p.put("srl.resource.loader.class", "com.platform.utils.VelocityUtils");
		// 进行初始化操作
		ve.init(p);
		// 加载模板，设定模板编码
		Template t = ve.getTemplate(template, "utf-8");
		// 设置初始化数据
		VelocityContext context = new VelocityContext();
		context.put("context", obj);
		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);
		String text=StringUtils.replace(writer.toString(), " ", "");
		text=StringUtils.replace(text, "\r", "");
		text=StringUtils.replace(text, "\n", "");
		return text;
	}
	
	
	public static void getTpl2() throws Exception{
		Map map = new HashMap();
		map.put("name", "张三");
		map.put("project", "Jakarta");
		String template = "你好 $name !\r\n$project project.";
		StringBuffer sb = new StringBuffer();
		sb.append("#foreach($member in $context.entrySet())")
		  .append("<li>$member.key - $member.value</li>\r\n")
		  .append("#end");
		template = sb.toString();

		String result = VelocityUtils.render(map, template);
		System.out.println(result);
	}
	
	public static void getTpl3() throws ParseErrorException{
		List context = new ArrayList();
		context.add("aa");
		context.add("bb");
		StringBuffer template = new StringBuffer();
		template.append("#foreach($item in $context)")
		  .append("$item")
		  .append("#end");
		String result = VelocityUtils.render(context, template.toString());
		System.out.println(result);
	}

	


	public static void main(String[] args) throws ParseErrorException {
		VelocityUtils.getTpl3();
	}

	@Override
	public long getLastModified(Resource arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InputStream getResourceStream(String arg0) throws ResourceNotFoundException {
		InputStream result = null;

		if (arg0 == null || arg0.length() == 0) {
			throw new ResourceNotFoundException("模板没有被定义~！");
		}
		result = new ByteArrayInputStream(arg0.getBytes());
		return result;
	}

	@Override
	public void init(ExtendedProperties arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSourceModified(Resource arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
