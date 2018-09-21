package com.mingliang.lms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mingliang.lms.utils.CustomDateTimeSerializer;

/**
 * 
 * @ClassName: Log
 * @Description: 系统日志实体
 * @author Michael.Ma
 * @date 2018年9月13日 下午5:59:09
 *
 */
@Entity
@Table(name="t_log")
public class Log {

	public final static String LOGIN_ACTION="登录操作";
	public final static String LOGOUT_ACTION="注销操作";
	public final static String SEARCH_ACTION="查询操作";
	public final static String UPDATE_ACTION="更新操作";
	public final static String ADD_ACTION="添加操作";
	public final static String DELETE_ACTION="删除操作";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 编号
	
	@Column(length=100)
	private String type; // 日志类型
	
	@Column(length=1000)
	private String content; // 操作内容
	
	@Column(length=50)
	private String loginUser; //登录人
	
	@Temporal(TemporalType.TIMESTAMP) 
	private Date time; // 操作时间

	@Transient
	private Date btime; // 起始时间  搜索用到
	
	@Transient
	private Date etime; // 结束时间  搜索用到

//	public Log() {
//		super();
//	}

	public Log(String type,String content) {
		this.type = type;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonSerialize(using=CustomDateTimeSerializer.class)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getBtime() {
		return btime;
	}

	public void setBtime(Date btime) {
		this.btime = btime;
	}

	public Date getEtime() {
		return etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	
	
	
}
