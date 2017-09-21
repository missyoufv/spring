package com.study.springboot.server.domain;

import java.io.Serializable;

public class OrderInfo implements Serializable{
	
	private String orderNo;
	
	private String amount;
	
	private String status;
	

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderNo=" + orderNo + ", amount=" + amount
				+ ", status=" + status + "]";
	}
	
	

}
