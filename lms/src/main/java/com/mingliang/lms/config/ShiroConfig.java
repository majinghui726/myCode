package com.mingliang.lms.config;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * 
 * @ClassName: ShiroConfig
 * @Description: Shiro配置类
 * 1.配置ShiroFilterFactory 
 * 2.配置SecurityManager
 * @author Michael.Ma
 * @date 2018年9月3日 下午4:27:02
 *
 */
@Configuration
public class ShiroConfig {
	private static final Logger logger = LogManager.getLogger(ShiroConfig.class);
	
	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年9月3日 下午4:29:00
	 * @Title: shiroFilter
	 * @Description: 配置shiro过滤器
	 * @param @param securityManager
	 * @param @return  参数
	 * @return ShiroFilterFactoryBean  返回类型
	 * @throws
	 */
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		//1.定义shiroFactoryBean
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//2.设置securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//3.如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/");
		//4.设置成功之后要跳转的链接
//		shiroFilterFactoryBean.setSuccessUrl("/index.html");
		//5.设置未授权界面
//		shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");
		//6.LinkedHashMap是有序的，进行顺序拦截器配置
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		// 配置不会被拦截的链接 顺序判断，因为前端模板采用了thymeleaf，这里不能直接使用 ("/static/**", "anon")来配置匿名访问，必须配置到每个静态目录
		filterChainDefinitionMap.put("/bootstrap3/**", "anon");//匿名访问静态资源
		filterChainDefinitionMap.put("/css/**", "anon"); 
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		
		filterChainDefinitionMap.put("/user/login", "anon");
		filterChainDefinitionMap.put("/drawImage", "anon");
		//配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		//7.所有url必须通过认证才可以访问 (authc:所有url都必须认证通过才可以访问; anon:所有url都可以匿名访问)
		//过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		filterChainDefinitionMap.put("/**","authc");
		//8.设置shiroFilterFactoryBean的FilterChainDefinitionMap
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		logger.info("==================Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}
	
	
	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年9月3日 下午4:40:59
	 * @Title: securityManager
	 * @Description: 配置安全管理器
	 * @param @return  参数
	 * @return SecurityManager  返回类型
	 * @throws
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//设置realm.
		securityManager.setRealm(userRealm());//将自定义的realm注入到securityManager中
		return securityManager;
	}
	
	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年9月3日 下午6:19:23
	 * @Title: myRealm
	 * @Description: 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 * @param @return  参数
	 * @return MyRealm  返回类型
	 * @throws
	 */
	@Bean
	public UserRealm userRealm() {
		UserRealm userRealm = new UserRealm();
		return userRealm;
	}
	
	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年9月3日 下午5:41:04
	 * @Title: lifecycleBeanPostProcessor
	 * @Description: Shiro生命周期处理器
	 * @param @return  参数
	 * @return LifecycleBeanPostProcessor 返回类型
	 * @throws
	 */
//	@Bean
//	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//		return new LifecycleBeanPostProcessor();
//		
//	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

}
