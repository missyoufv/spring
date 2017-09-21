package com.study.springboot.server.service;

import com.study.springboot.server.dao.IOrderDao;
import com.study.springboot.server.domain.OrderInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	@Autowired
	private IOrderDao orderDao;
	
	public OrderInfo queryOrderInfoByOrderNo(String orderNo){
		return orderDao.queryEnetityByNo(orderNo);
	}

}
