package com.platform.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 应用程序启动销毁监听器
 * @author zhuhaojie
 *
 */
public class WebAppContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("项目销毁");
	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context =event.getServletContext();
		//Const.WEB_APP_CONTEXT=(ApplicationContext) context;
		//Const.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(context);
		System.out.println("项目启动: 根目录为:"+ context.getRealPath("/"));
		
	}

}
