package com.study.springboot.server.dao;

import com.study.springboot.server.domain.OrderInfo;


public interface IOrderDao {

	OrderInfo queryEnetityByNo(String orderNo);

	
	
}
