package com.mingliang.lms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.mingliang.lms.domain.Menu;



public interface MenuDao {
	
	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年9月3日 下午6:14:25
	 * @Title: findByRoleId
	 * @Description: 根据id获取权限菜单集合
	 * @param @param roleId
	 * @param @return  参数
	 * @return List<Menu>  返回类型
	 * @throws
	 */
	@Select("SELECT m.* FROM t_role r,t_menu m,t_role_menu rm WHERE rm.`role_id`=r.`id` AND rm.`menu_id`=m.`id` AND r.`id` = #{roleId}")
	public List<Menu> findByRoleId(int roleId);
	
	
	@Select("select * from t_menu where p_id=#{parentId} and id in (SELECT menu_id FROM t_role_menu WHERE role_id=#{roleId})")
	public List<Menu> findByParentIdAndRoleId(@Param("parentId") int parentId,@Param("roleId") int roleId);

}
