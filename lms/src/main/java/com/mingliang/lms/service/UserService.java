package com.mingliang.lms.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.mingliang.lms.domain.User;

public interface UserService {
	
	public void insertUser(User user);
	
	public void deleteUser(String uuid);
	
	public void updateUser(User user);
	
	public User selectOneByUUID(String uuid);
	
	public List<User> selectUserAll();
	
	public PageInfo<User> queryUsers(String userName,int pageNum,int pageSize);
	
	
	public User findByUserName(String userName);
	
}
