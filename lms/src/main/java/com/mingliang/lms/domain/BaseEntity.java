package com.mingliang.lms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

public class BaseEntity implements Serializable{
	
	/**
	 * @author Michael.Ma
	 * @date 2018年8月28日 下午6:07:26
	 * @Fields serialVersionUID : 公用属性
	 */
	
	private static final long serialVersionUID = 1L;
	private Date createDate;  // 创建日期
	private Date updateDate;  // 更新日期
	private Integer version;  // 版本号
	private boolean deletion; //是否删除
	private String createUser;//创建者
	private String updateUser;//更新者
	
	//不是表中字段的属性必须加 @Transient
	@Transient
	private int pageNum = 1;
	@Transient
	private int pageSize = 10;
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public boolean isDeletion() {
		return deletion;
	}
	public void setDeletion(boolean deletion) {
		this.deletion = deletion;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	

}
