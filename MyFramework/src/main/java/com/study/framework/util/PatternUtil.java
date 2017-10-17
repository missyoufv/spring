package com.study.framework.util;

/**
 * 正则表达式的应用（匹配，切割，替换，获取）
 * 前三者用String提供的方法也可以实现，第四种必须要用pattern类
 * @author admin
 *
 */
public class PatternUtil {
	
	public static void main(String[] args) {
		
		/**
		 * 详细讲解关于正则表达式 的使用
		 */
		String regex = "1[58]\\d{9}";
		String phone = "15767541175";
		System.out.println(phone.matches(regex));
		
		//使用分组进行敏感信息处理
		String reg1 = "(\\d{3})\\d{4}(\\d{4})";
		String temp =phone.replaceAll(reg1, "$1****$2");
		System.out.println(temp);
		
		//多个重复的字符替换成一个
		String name = "zhangsanyyylisigggwangwuaaaaa";
		String reg2 = "(.)\\1+";//分组 并编号为1  这样的组有1个以上
		
		System.out.println(name.replaceAll(reg2, "$1"));
		
		String fileName = "td_808080211881314_20171016_51EE287957DD64E1C9CFCA72B85AF402.txt.zip";
		String reg3="td_808080211881314_20171016_[A-Za-z0-9]{32}.txt.zip";
		
		System.out.println(fileName.matches(reg3));
		
	}
}
