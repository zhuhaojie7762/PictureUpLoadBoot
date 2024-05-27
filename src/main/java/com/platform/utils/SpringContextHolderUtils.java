
package com.platform.utils;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * 以静态变量保存Spring ApplicationContext, 
 * 可在任何代码任何地方任何时候取出ApplicaitonContext
 */
/**
 * @Service
 * @Lazy(false)
 * @author zhuhaojie
 * 2016年12月26日 上午10:58:19
 */

public class SpringContextHolderUtils implements ApplicationContextAware, DisposableBean {

	/**
	 * 容器对象
	 */
	private static ApplicationContext applicationContext = null;
    
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(SpringContextHolderUtils.class);
	
	
	
	/**
	 * 封闭默认构造方法
	 * @author zhuhaojie
	 * @time 2016年12月27日 上午9:55:04
	 */
	private SpringContextHolderUtils() {
	    
	}

	
	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年12月26日 上午10:58:59
	 * @return ApplicationContext 取得存储在静态变量中的ApplicationContext对象
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	
	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年12月26日 上午10:59:43
	 * @param name 指定获取的bean名称
	 * @return T 从静态变量applicationContext中取得Bean,
	 *  自动转型为所赋值对象的类型
	 *  @param <T> 返回指定类型的bean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	
	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年12月26日 上午11:01:06
	 * @param requiredType 指定获取的bean类型
	 * @return T 从静态变量applicationContext中取得Bean, 
	 * 自动转型为所赋值对象的类型
	 * @param <T>  返回指定类型的bean
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	
	/**
	 * 
	 * @author zhuhaojie
	 * @time 2016年12月26日 上午11:02:12
	 * 设置 SpringContextHolder中的ApplicationContext为Null.
	 */
	public static void clearHolder() {
		if (logger.isDebugEnabled()) {
			logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
		}
		applicationContext = null;
	}

	

	/**
	 * 实现ApplicationContextAware接口, 将spring容器注入Context到静态变量中.
	 * @author zhuhaojie
	 * @time 2016年12月26日 上午11:02:51
	 * @param applicationContext spring框架注入进来的spring上下文对象
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextHolderUtils.applicationContext = applicationContext;
	}

	/**
	 * 实现DisposableBean接口, 在Context关闭时清理静态变量.
	 */
	@Override
	public void destroy() throws Exception {
		SpringContextHolderUtils.clearHolder();
	}

	
	/**
	 * 检查ApplicationContext是否为null
	 * @author zhuhaojie
	 * @time 2016年12月26日 上午11:04:23
	 */
	private static void assertContextInjected() {
		Validate.validState(applicationContext != null, 
				    "applicaitonContext属性未注入, "
				  + "请在applicationContext.xml中定义SpringContextHolder.");
	}
}