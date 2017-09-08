package com.study.springcloud.rabbitmq.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 异步通知 通知失败 可以将通知实体 放置到队列中 ，定期轮询通知
 * @author admin
 *
 */
public class PaymentNotifyVo implements Serializable{
	
	private String orderNo;
	private int status;
	private String merNo;
	private BigDecimal amount;
	//........
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	

}
