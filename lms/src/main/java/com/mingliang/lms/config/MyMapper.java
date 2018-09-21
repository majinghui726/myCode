package com.mingliang.lms.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
/**
 * 
 * @ClassName: MyMapper
 * @Description: 解决单表增删改查，基于Mybatis的插件
 * @author Michael.Ma
 * @date 2018年8月28日 下午4:17:16
 *
 * @param <T>
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

	
}
