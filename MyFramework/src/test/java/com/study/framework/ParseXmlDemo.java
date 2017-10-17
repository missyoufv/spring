package com.study.framework;

import org.junit.Test;

import com.study.framework.util.ParseUtils;

public class ParseXmlDemo {
	
	@Test
	public void testXml(){
		String className = ParseUtils.getClassName("/member/login");
		System.out.println(className);
	}

}
