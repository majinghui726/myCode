package com.mingliang.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingliang.lms.dao.RoleDao;
import com.mingliang.lms.domain.Role;
import com.mingliang.lms.service.RoleService;


@Service
@Transactional(readOnly=true,rollbackFor=Exception.class)
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> findByUserId(Integer id) {
		return roleDao.findByUserId(id);
	}

	@Override
	public Role findById(Integer id) {
		return roleDao.selectByPrimaryKey(id);
	}

}
