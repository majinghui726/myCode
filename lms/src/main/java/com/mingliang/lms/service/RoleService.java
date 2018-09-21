package com.mingliang.lms.service;

import java.util.List;

import com.mingliang.lms.domain.Role;

public interface RoleService {
	
	public List<Role> findByUserId(Integer id);
	
	public Role findById(Integer id);

}
