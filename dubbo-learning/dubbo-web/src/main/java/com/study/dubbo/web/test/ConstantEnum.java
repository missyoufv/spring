package com.study.dubbo.web.test;

import java.util.HashMap;
import java.util.Map;

public enum ConstantEnum {
	
	one(1,"张三",18),
	two(2,"李四",20);
	
	private static Map<Integer, ConstantEnum> tradeTypeCodeMap = null;
	
	private int id;
	private String name;
	private int age;
	private ConstantEnum(int id,String name,int age){
		this.id = id;
		this.name = name;
		this.age =age;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	
    public static ConstantEnum getById(int id) {
        if (tradeTypeCodeMap == null) {
            synchronized (ConstantEnum.class) {
                if (tradeTypeCodeMap == null) {
                    tradeTypeCodeMap = new HashMap<Integer, ConstantEnum>();
                    for (ConstantEnum payType : ConstantEnum.values()) {
                        tradeTypeCodeMap.put(payType.getId(), payType);
                    }
                }
            }
        }
        return tradeTypeCodeMap.get(id);
    }
}
