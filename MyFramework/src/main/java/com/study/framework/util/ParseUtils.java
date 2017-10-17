package com.study.framework.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析web.xml 封装到map中
 * 解析xml的技术用的是dom4j 将数据读到内存
 * 
 * @author admin
 *
 */
public class ParseUtils {
	
	private static Map<String,String> map;
	/**
	 * 私有构造函数
	 */
	private ParseUtils(){
		
	}
	
	public static String getClassName(String key){
		
		if(map == null){
			synchronized (ParseUtils.class) {
				if(map == null){
					map = parseXmlToMap();
				}
			}
		}
		return map.get(key);
	}

	/**
	 * 默认解析resource下面的web.xml
	 */
	private static Map<String,String> parseXmlToMap() {
		Map<String,String> dataMap = new HashMap<String,String>();
		try{
			InputStream inputStream = ParseUtils.class.getClassLoader().getResourceAsStream("web.xml");
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputStream);
			Element root = document.getRootElement();
			System.out.println("根节点的name为:"+root.getName());
			//从根节点遍历子节点
			for(Iterator iterator = root.elementIterator();iterator.hasNext();){
				Element element = (Element) iterator.next();
				Element urlNode = (Element) element.elements("url-pattern").get(0);
				Element classNode = (Element) element.elements("servlet-class").get(0);
				
				String key  = urlNode.getText();
				String value = classNode.getText();
				System.out.println("key="+key +",value="+value);
				
				dataMap.put(key, value);
			}
			return dataMap;
		}catch(Exception ex){
			System.out.println("解析配置文件异常,"+ex);
			return null;
		}
		
	}

}
