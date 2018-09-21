package com.mingliang.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingliang.lms.dao.MenuDao;
import com.mingliang.lms.domain.Menu;
import com.mingliang.lms.service.MenuService;

@Service
@Transactional(readOnly=true,rollbackFor=Exception.class)
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDao menuDao;

	@Override
	public Menu findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> findByRoleId(int roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> findByParentIdAndRoleId(int parentId, int roleId) {
		return menuDao.findByParentIdAndRoleId(parentId, roleId);
	}

	@Override
	public List<Menu> findByParentId(int parentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
