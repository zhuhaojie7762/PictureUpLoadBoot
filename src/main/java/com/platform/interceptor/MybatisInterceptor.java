package com.platform.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
/**
 * @Interceptor 代表当前对象是一个Interceptor
 * @Signature 代表此拦截器要拦截的方法，接口 参数等
 *每一个Signature代表一个拦截点
 *可以有多个
 *这里定义了两个代表两个拦截点
 *第一个拦截点:
 *  type:指定拦截方法所属接口类型
 *  method:代表此拦截点拦截的方法名称为query
 *  args:代表方法的参数列表 
 * 那么第一个拦截点就是要拦截 Exceutor接口的参数类型为MappedStatement和 Object的名称为update的方法
 * 第二个拦截点：
 *    要拦截Exceutor接口的参数类型为MappedStatement,Object,RowBounds,ResultHandler的query方法
 *    
 *    当代理对象执行query或者update方法时就会触发
 *    intercept()方法改变查询的sql
 * @author zhuhaojie
 * Mybatis拦截器只能拦截四种类型的接口：Executor、StatementHandler、ParameterHandler和ResultSetHandler
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }) })
public class MybatisInterceptor implements Interceptor {
	private static Logger logger = LoggerFactory.getLogger(MybatisInterceptor.class);

	private Properties properties;

	/**
	 * 拦截器要执行的方法
	 * 进行方法拦截时要执行的方法
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = null;
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
		}
		String sqlId = mappedStatement.getId();
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		Configuration configuration = mappedStatement.getConfiguration();
		Object returnValue;
		long start = System.currentTimeMillis();
		returnValue = invocation.proceed();
		long end = System.currentTimeMillis();
		long time = (end - start);
		String sql = getSql(configuration, boundSql, sqlId, time);
		if (time > 1000) {
			logger.warn("out 1s sql="+sql);
		}else{
			logger.info(sql);
		}
		return returnValue;
	}

	public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
		String sql = showSql(configuration, boundSql);
		return sqlId +
				":" +
				sql +
				":" +
				time +
				"ms";
	}

	private static String getParameterValue(Object obj) {
		String value;
		if (obj instanceof String) {
			value = "'" + obj + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(obj) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}

	public static String showSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						String value =getParameterValue(obj);
						//System.out.println("value:"+value);
						sql = sql.replaceFirst("\\?",value);
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						String value =getParameterValue(obj);
						//System.out.println("value:"+value);
						sql = sql.replaceFirst("\\?",value);
					}
				}
			}
		}
		return sql;
	}

	/**
	 * 拦截器需要拦截的对象
	 * 拦截器用来封装目标对象
	 * 可以返回目标对象本身
	 * 也可以返回目标对象的代理对象
	 */
	public Object plugin(Object target) {
		/**
		 * 可以决定要返回的对象是目标对象还是对应的代理
		 * 由此类的注解中的type指定
		 * 此类type只有Executor
		 * 当mybatis要用Executor对象时会返回代理对象
		 * 其他都是目标对象
		 */
		return Plugin.wrap(target, this);
	}
    /**
     *当前拦截器被初始化时调用此方法
     *传入在xml中配置的属性
     */
	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
		
	}
}