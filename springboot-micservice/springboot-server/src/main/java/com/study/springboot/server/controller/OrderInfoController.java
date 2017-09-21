package com.study.springboot.server.controller;

import com.study.springboot.server.domain.OrderInfo;
import com.study.springboot.server.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/order")
public class OrderInfoController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="queryByNo/{orderNo}",produces = { "application/json;charset=UTF-8" })
	public OrderInfo queryOrderInfo(@PathVariable(name="orderNo")String orderNo){
		OrderInfo orderInfo = orderService.queryOrderInfoByOrderNo(orderNo);
		System.out.println("查询订单信息为"+orderInfo);
		return orderInfo;
	}

}
