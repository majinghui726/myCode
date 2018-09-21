package com.mingliang.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mingliang.lms.dao.OrderDao;
import com.mingliang.lms.domain.Order;
import com.mingliang.lms.service.OrderService;

@Service
@Transactional(readOnly=true,rollbackFor=Exception.class)
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;


	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public int insert(Order order) {
		return orderDao.insert(order);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public void delete(Order order) {
		orderDao.delete(order);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public int update(Order order) {
		return orderDao.updateByPrimaryKey(order);
	}

	@Override
	public Order selectOne(Order order) {
		return orderDao.selectOne(order);
	}

	@Override
	public List<Order> selectOrders() {
		List<Order> orders = orderDao.selectAll();
		return orders;
	}

}
