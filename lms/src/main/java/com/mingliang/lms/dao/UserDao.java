package com.mingliang.lms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.mingliang.lms.domain.User;

public interface UserDao {
	
	@Insert("insert b_user(uuid,user_code,passWord,user_name,status,role_code,plant_code,email,create_date,update_date) values (#{uuid},#{userCode},#{passWord},#{userName},#{status},#{roleCode},#{plantCode},#{email},#{createDate},#{updateDate})")
	public int insert(User user);
	
	@Delete("delete from b_user where uuid=#{uuid}")
	public void deleteUser(String uuid);
	
	@Update("update b_user set user_name=#{userName},status=#{status},user_code=#{userCode},update_date=#{updateDate} where uuid=#{uuid}")
	public void updateUser(User user);
	
	@Select("select uuid,user_code,passWord,user_name,status,role_code,plant_code,email,create_date,update_date from b_user where uuid = #{uuid}")
	public User selectOneByUUID(String uuid);
	
	@Select("select * from b_user where 1=1")
	public List<User> selectUserAll();
	
	
	//注：方法名和要UserMapper.xml中的id一致
	public List<User> queryUsers(@Param("userName") String userName);
	
	
	@Select("select * from t_user where user_name = #{userName}")
	public User findByUserName(String userName);
	
	
	
}
