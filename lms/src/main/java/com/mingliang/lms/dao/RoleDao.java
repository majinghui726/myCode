package com.mingliang.lms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.mingliang.lms.config.MyMapper;
import com.mingliang.lms.domain.Role;



public interface RoleDao extends MyMapper<Role> {
	
	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年9月3日 下午6:11:22
	 * @Title: findByUserId
	 * @Description: 根据用户id查询角色集合
	 * @param @param id
	 * @param @return  参数
	 * @return List<Role>  返回类型
	 * @throws
	 */
	@Select("SELECT r.* FROM t_user u,t_role r,t_user_role ur WHERE ur.user_id = u.`id` AND ur.role_id = r.`id` AND u.`id` = #{id}")
	public List<Role> findByUserId(Integer id);

}
