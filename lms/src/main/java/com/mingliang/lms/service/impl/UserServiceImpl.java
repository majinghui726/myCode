package com.mingliang.lms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mingliang.lms.dao.UserDao;
import com.mingliang.lms.domain.User;
import com.mingliang.lms.service.UserService;

@Service
@Transactional(readOnly=true,rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	//注意：方法的@Transactional会覆盖类上面声明的事务
	//Propagation.REQUIRED ：有事务就处于当前事务中，没事务就创建一个事务
	//isolation=Isolation.DEFAULT：事务数据库的默认隔离级别
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public void insertUser(User user) {
		userDao.insert(user);
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public void deleteUser(String uuid) {
		userDao.deleteUser(uuid);
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
	
	@Override
	public User selectOneByUUID(String uuid) {
		User user = userDao.selectOneByUUID(uuid);
		return user;
	}


	@Override
	public List<User> selectUserAll() {
		List<User> users = userDao.selectUserAll();
		return users;
	}


	@Override
	public PageInfo<User> queryUsers(String userName,int pageNum,int pageSize) {
		Page<User> page = PageHelper.startPage(pageNum, pageSize);
		//PageHelper会自动拦截到下面这查询sql
		userDao.queryUsers(userName);
		return page.toPageInfo();
	}


	@Override
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}
	
}
