package com.study.dubbo.web.test;


public class EnumDemo {
	
	public static void main(String[] args) {
		System.out.println(ConstantEnum.values());
		System.out.println(ConstantEnum.getById(1).getName());
		System.out.println(ConstantEnum.getById(2).getName());
	}
	
}
