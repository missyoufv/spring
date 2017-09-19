package com.study.springcloud.excel.vo;

public class UserInfo {
	
	private String username;
	
	private String cardNo;
	
	private String amount;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", cardNo=" + cardNo
				+ ", amount=" + amount + "]";
	}
	
	
	
	

}
