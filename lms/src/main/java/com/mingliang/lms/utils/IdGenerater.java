package com.mingliang.lms.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


/**
 * 
 * 
 **************************************************************** 
 *  
 * Title: IdGenerater.java
 * Description:获取主键
 **************************************************************** 
 * date 2018-8-14 下午3:19:59 
 * 
 * @author Michael.Ma
 */
public class IdGenerater {
	public static final String dtLong = "yyyyMMddHHmmssSSS";

	//获取时间+随机数 主键
	public static String getID() {
		return getOrderNum() + getFive();
	}

	//获取guid主键
	public static String getGUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase().replaceAll("-", "");
	}


	public static String getOrderNum() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 产生随机的五位数
	 * 
	 * @return
	 */
	public static String getFive() {
		Random rad = new Random();
		return rad.nextInt(100000) + "";
	}

	
//	public static void main(String[] args) {
//		for (int i = 0; i < 10; i++) {
//			logger.info(getID());
//		}
//
//	}

}
