package com.mingliang.lms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mingliang.lms.dao.LogDao;
import com.mingliang.lms.dao.UserDao;
import com.mingliang.lms.domain.Log;
import com.mingliang.lms.domain.User;
import com.mingliang.lms.service.LogService;

@Service
@Transactional(readOnly=true,rollbackFor=Exception.class)
public class logServiceImpl implements LogService {
	
	@Autowired
	private LogDao logDao;
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public void save(Log log) {
		log.setTime(new Date()); // 设置操作日期
		String userName = (String) SecurityUtils.getSubject().getPrincipal();
		User user = userDao.findByUserName(userName);
		log.setLoginUser(user.getTrueName()); // 设置用户名
		logDao.insert(log);
	}

	@Override
	public List<Log> list(Log log, Integer page, Integer pageSize, Direction direction, String... properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCount(Log log) {
		// TODO Auto-generated method stub
		return null;
	}

}
