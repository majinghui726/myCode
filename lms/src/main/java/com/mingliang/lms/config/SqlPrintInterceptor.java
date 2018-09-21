package com.mingliang.lms.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Intercepts
({  
	@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
	@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@Component
public class SqlPrintInterceptor implements Interceptor {
	private static final Logger logger = LogManager.getLogger(SqlPrintInterceptor.class);
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Object intercept(Invocation arg0) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) arg0.getArgs()[0];
		Object parameterObject = null;
		if (arg0.getArgs().length > 1) {
			parameterObject = arg0.getArgs()[1];
		}
		long start = System.currentTimeMillis();
		
		Object result = arg0.proceed();
		String statementId = mappedStatement.getId();
		BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
		Configuration configuration = mappedStatement.getConfiguration();
		String sql = getSql(boundSql, parameterObject, configuration);
		
		long end = System.currentTimeMillis();
		long timing = end - start;
		if(logger.isInfoEnabled()) {
			logger.info("执行sql耗时:" + timing + " ms" + " - 方法名:" + statementId);
			logger.info("执行sql==="+sql);
		}
		return result;
	}

	@Override
	public Object plugin(Object arg0) {
		if (arg0 instanceof Executor) {
			 return Plugin.wrap(arg0, this);
		}
		return arg0;
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
	}
	
	private String getSql(BoundSql boundSql, Object parameterObject, Configuration configuration) {
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
		if (parameterMappings != null) {
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else {
						MetaObject metaObject = configuration.newMetaObject(parameterObject);
						value = metaObject.getValue(propertyName);
					}
					sql = replacePlaceholder(sql, value);
				}
			}
		}
		return sql;
	}
	
	private String replacePlaceholder(String sql, Object propertyValue) {
		String result;
		if (propertyValue != null) {
			if (propertyValue instanceof String) {
				result = "'" + propertyValue + "'";
			} else if (propertyValue instanceof Date) {
				result = "'" + DATE_FORMAT.format(propertyValue) + "'";
			} else {
				result = propertyValue.toString();
			}
		} else {
			result = "null";
		}
		return sql.replaceFirst("\\?", Matcher.quoteReplacement(result));
		
	}

}
