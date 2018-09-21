package com.mingliang.lms.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;

public class SpringInit extends WebApplicationObjectSupport {
	private static ApplicationContext applicationContext = null;

	@Override
	protected void initApplicationContext(ApplicationContext context) throws BeansException {
		super.initApplicationContext(context);
		if (SpringInit.applicationContext == null) {
			SpringInit.applicationContext = context;
			logger.info("=======================================");
			logger.info("==========Init Srping ApplictionContext Success ===========");
			logger.info("=======================================");
		}

//		Constant.baseService =  (BaseService) getBean("baseService");
//		BaseDao baseDao = (BaseDao) getBean("baseDao");
//		String sql = " select node_code ,node_name  from  zc_flow order by ordinal";
//		List<Map<String, Object>> nodeInfo = baseDao.Query(sql, null);
//		for (Map<String, Object> map : nodeInfo) {
//			Constant.NODE_NAME_MAP.put(String.valueOf(map.get("node_code")), String.valueOf(map.get("node_name")));
//		}
	}

	public ApplicationContext getAppContext() {
		return applicationContext;
	}

	public static  Object getBean(String name) {
		return applicationContext.getBean(name);
	}
}
