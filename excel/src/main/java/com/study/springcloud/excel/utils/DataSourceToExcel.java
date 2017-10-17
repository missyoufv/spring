//package com.study.springcloud.excel.utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//
//public class DataSourceToExcel {
//
//	
//	
//	/**
//	 * 读取excel中的数据 插入到数据库中
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//
//		Connection conn = null;
//		PreparedStatement ps = null;
//		String sql = "";
//		try {
//			sql = parseExcelToSql("cardinfo.xls");
//			
//			System.out.println("批量导入的sql为:"+sql);
//			conn = JdbcUtils.getConnection();
//			ps = conn.prepareStatement(sql);
//			ps.execute();
//			System.out.println("批量导入数据结束-----------");
//			
//		} catch (Exception ex) {
//			System.out.println("导出数据异常" + ex);
//		}
//
//	}
//
//	private static String parseExcelToSql(String filePath) throws Exception {
//		
//		String sql = " insert into cardbininfo(bank_name,card_name,card_len,card_bin,bin_len,card_kind,bank_code)value";
//		
//		ClassLoader classLoader = DataSourceToExcel.class.getClassLoader();
//		
//		/**
//        	getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
//        */
//		
//		URL url = classLoader.getResource(filePath);
//        /**
//         * url.getFile() 得到这个文件的绝对路径
//         */
//        System.out.println(url.getFile());
//        
//		File file = new File(url.getFile());
//		if (!file.getParentFile().exists())
//			file.getParentFile().mkdirs();
//
//		//该种写法兼容excel 2003 
//		FileInputStream inputStream = new FileInputStream(filePath);
//
//	    Workbook workbook = WorkbookFactory.create(inputStream);  
//	    Sheet sheet = workbook.getSheetAt(0);  //示意访问sheet  
//		Row rowData;
//
//		for (int i = sheet.getTopRow(); i < sheet.getLastRowNum()+1; i++) {
//			rowData = sheet.getRow(i);
//			String bankName = "";
//			String cardName = "";
//			String cardLen="";
//			String cardBin="";
//			String binLen="";
//			String cardKind="";
//			String bankCode="";
//			for (int j = rowData.getFirstCellNum(); j < rowData.getLastCellNum(); j++) {
//				//解析每行数据 并封装到对应的字段中
//				switch(j)
//				{
//					case 0:bankName = rowData.getCell(j).toString().substring(0,rowData.getCell(j).toString().trim().length()-10).trim();break;
//					case 1:cardName = rowData.getCell(j).toString().trim();break;
//					case 8:cardLen = rowData.getCell(j).toString().trim();break;
//					case 12:binLen = rowData.getCell(j).toString().trim();break;
//					case 13:cardBin = rowData.getCell(j).toString().trim();break;
//					case 15:cardKind = parseCardKind(rowData.getCell(j).toString());break;
//					case 16:bankCode = parseBankCode(bankName);break;
//					
//				}
//				
//			}
//			
//		    sql += "('"+bankName+"','"+cardName+"','"+cardLen+"','"+cardBin+"','"+binLen+"','"+cardKind+"','"+bankCode+"'),";
//
//		}
//
//		return sql.substring(0, sql.length()-1);
//	}
//	
//	/**
//	 * 根据bankName获取对应的bankCode;
//	 * @param bankName
//	 * @return
//	 */
//	private static String parseBankCode(String bankName) {
//		
//        String bankCode = getMapValue(bankName);
//        if(bankCode == null || bankCode.isEmpty())
//        	System.out.println(bankName+"没有对应的bankcode");
//		return bankCode;
//	}
//
//	private static String getMapValue(String bankName) {
//		Map<String, String> bankMap = new HashMap<String, String>();
//        bankMap.put("北京银行", "100");
//        bankMap.put("中国工商银行", "102");
//        bankMap.put("中国农业银行", "103");
//        bankMap.put("中国银行", "104");
//        bankMap.put("中国建设银行", "105");
//        bankMap.put("国家开发银行", "201");
//        bankMap.put("中国进出口银行", "202");
//        bankMap.put("中国农业发展银行", "203");
//        bankMap.put("交通银行", "301");
//        bankMap.put("中信银行", "302");
//        bankMap.put("中国光大银行", "303");
//        bankMap.put("华夏银行", "304");
//        bankMap.put("中国民生银行", "305");
//        bankMap.put("广东发展银行", "306");
//        bankMap.put("平安银行", "307");
//        bankMap.put("招商银行", "308");
//        bankMap.put("兴业银行", "309");
//        bankMap.put("上海浦东发展银行", "310");
//        bankMap.put("城市商业银行", "313");
//        bankMap.put("农村商业银行", "314");
//        bankMap.put("吉林农信联合社", "1445");
//        bankMap.put("甘肃省农村信用社联合社", "1453");
//        bankMap.put("黑龙江省农村信用社联合社", "1457");
//        bankMap.put("武汉农村商业银行", "1459");
//        bankMap.put("山东农村信用联合社", "1414");
//        bankMap.put("江苏长江商业银行", "0493");
//        bankMap.put("六盘水商行", "0500");
//        bankMap.put("恒丰银行", "315");
//        bankMap.put("浙商银行", "316");
//        bankMap.put("农村合作银行", "317");
//        bankMap.put("渤海银行股份有限公司", "318");
//        bankMap.put("徽商银行股份有限公司", "319");
//        bankMap.put("镇银行有限责任公司", "320");
//        bankMap.put("城市信用社", "401");
//        bankMap.put("农村信用社", "402");
//        bankMap.put("中国邮政储蓄银行", "403");
//        bankMap.put("宁波银行", "408");
//        bankMap.put("汇丰银行", "501");
//        bankMap.put("东亚银行", "502");
//        bankMap.put("南洋商业银行", "503");
//        bankMap.put("恒生银行(中国)有限公司", "504");
//        bankMap.put("中国银行（香港）有限公司", "505");
//        bankMap.put("珠海南通银行", "771");
//        bankMap.put("宁波国际银行", "772");
//        bankMap.put("新联商业银行", "773");
//        bankMap.put("厦门国际银行", "781");
//        bankMap.put("上海－巴黎国际银行", "782");
//        bankMap.put("福建亚洲银行", "783");
//        bankMap.put("浙江商业银行", "784");
//        bankMap.put("华商银行", "785");
//        bankMap.put("青岛国际银行", "786");
//        bankMap.put("华一银行", "787");
//        bankMap.put("(澳门地区)银行", "969");
//        bankMap.put("(香港地区)银行", "989");
//		return bankMap.get(bankName.trim());
//	}
//
//	private static String parseCardKind(String cardKind) {
//		
//		if("借记卡".equals(cardKind.trim()))
//			return "1";
//		else if("贷记卡".equals(cardKind.trim()))
//			return "2";
//		else if("准贷记卡".equals(cardKind.trim()))
//			return "3";
//		else if("预付费卡".equals(cardKind.trim()))
//			return "4";
//		else
//			return null;
//	}
//
////	private static Object fillData(ResultSet rs, ArrayList<String> list,
////			Class clazz) throws Exception {
////
////		Object object = clazz.newInstance();
////		for (int i = 0; i < list.size(); i++) {
////			// 获取属性名
////			String propertyName = list.get(i);
////			// 通过反射获取属性对象
////			Field field = clazz.getDeclaredField(propertyName);
////			// 获取属性类型
////			Class type = field.getType();
////			String methodName = "set".concat(propertyName.substring(0, 1)
////					.toUpperCase().concat(propertyName.substring(1)));
////			Method method = clazz.getMethod(methodName, type);
////			if (method != null) {
////				method.setAccessible(true);
////				method.invoke(object, rs.getString(i + 1));
////			}
////
////		}
////		return object;
////	}
//
//}
