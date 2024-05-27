package com.platform.interceptor;

import java.util.LinkedHashMap;

/**
 * 用来配置 url和权限关系
 * 这里可以从数据库读取
 * @author zhuhaojie
 *
 */
public class FilterChainDefinitionMapBuilder {

	public  LinkedHashMap<String,String> builderFilterChainDefinitionMap(){
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		/**
		 * 读取数据库对map进行初始化
		 */
		/**  authc 需要登录
		 * 放置时是有顺序的
		 
		map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/user.jsp", "roles[user]");
		map.put("/admin.jsp", "roles[admin]");
		*/
		//map.put("/login.jsp", "anon");
		//map.put("/login/**","anon");
		map.put("/login/loginview","anon");
		
		map.put("/login/toLogin","anon");
		
		map.put("/resource2/**", "anon");
		map.put("/resource/**", "anon");
		map.put("/logout", "logout");
		map.put("/code.do", "anon");
		
		map.put("/**", "authc");
		return map;
	}
}
