package com.mingliang.lms.service;

import java.util.List;

import com.mingliang.lms.domain.Order;

public interface OrderService {
	
	public int insert(Order order);
	
	public void delete(Order order);
	
	public int update(Order order);
	
	public Order selectOne(Order order);
	
	public List<Order> selectOrders();

}
