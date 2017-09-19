package com.study.springcloud.excel.vo;

public class OrderInfo {
	
	private String orderNo;
	private String status;
	private String amount;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "OrderInfo [orderNo=" + orderNo + ", status=" + status
				+ ", amount=" + amount + "]";
	}
	
	
	
	

}
