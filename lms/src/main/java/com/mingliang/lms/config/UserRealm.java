package com.mingliang.lms.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mingliang.lms.dao.MenuDao;
import com.mingliang.lms.dao.RoleDao;
import com.mingliang.lms.dao.UserDao;
import com.mingliang.lms.domain.Menu;
import com.mingliang.lms.domain.Role;
import com.mingliang.lms.domain.User;

@Component
public class UserRealm extends AuthorizingRealm {
	private static final Logger logger = LogManager.getLogger(UserRealm.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;

	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年9月4日 下午1:53:47
	 *(非 Javadoc)
	 * <p>Title: doGetAuthorizationInfo</p>
	 * <p>Description: 认证信息(身份验证) Authentication 是用来验证用户身份</p>
	 * @param principals
	 * @return
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("==================UserRealm.doGetAuthenticationInfo()");
		// 获取用户的输入帐号
		String userName = (String) SecurityUtils.getSubject().getPrincipal();
		//通过username从数据库中查找 User对象
		User userInfo = userDao.findByUserName(userName);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		List<Role> roleList = roleDao.findByUserId(userInfo.getId());
		Set<String> roles = new HashSet<String>();
		for(Role role:roleList){
			roles.add(role.getName());
			List<Menu> menuList = menuDao.findByRoleId(role.getId());
			for(Menu menu:menuList){
				authorizationInfo.addStringPermission(menu.getName()); // 添加权限
			}
		}
		authorizationInfo.setRoles(roles);
		return authorizationInfo;
	}

	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年9月4日 下午3:15:20
	 *(非 Javadoc)
	 * <p>Title: doGetAuthenticationInfo</p>
	 * <p>Description: 权限认证</p>
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		try {
			User user = userDao.findByUserName(userName);
			if (user != null) {
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"xxx");
				return authcInfo;
			}else{
				return null;				
			}
		} catch (AuthenticationException e) {
			logger.error("身份认证失败");
			e.printStackTrace();
		}
		return null;
	}

}
