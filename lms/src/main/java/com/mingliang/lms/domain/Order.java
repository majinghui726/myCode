package com.mingliang.lms.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "b_order")
public class Order implements Serializable {

	/**
	 * @author Michael.Ma
	 * @date 2018年8月29日 下午2:56:06
	 * @Fields serialVersionUID : 实体类
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	private String uuid;     // uuid
	private String soNum;    // 订单号
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSoNum() {
		return soNum;
	}
	public void setSoNum(String soNum) {
		this.soNum = soNum;
	}

}
