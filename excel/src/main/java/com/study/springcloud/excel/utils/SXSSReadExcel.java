//package com.study.springcloud.excel.utils;
//
//import java.io.FileInputStream;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import com.study.springcloud.excel.vo.UserInfo;
//
//public class SXSSReadExcel {
//
//
//    public static void main(String[] args) throws Throwable {
//        FileInputStream inputStream = new FileInputStream("C:/Users/admin/Desktop/b3.xlsx");
//        
//      //该种写法不兼容excel 2003 
//        XSSFWorkbook xsffWorkbook = new XSSFWorkbook(inputStream);
//
//
//        XSSFSheet  sheet = xsffWorkbook.getSheetAt(0);//第一个shit
//        
//        XSSFRow rowData;
//        
//        List<UserInfo> list = new ArrayList<UserInfo>();
//        
//        Set<List<UserInfo>> set = new HashSet<List<UserInfo>>();
//        
//        BigDecimal total = BigDecimal.ZERO;
//        System.out.println(sheet.getTopRow());
//        for (int i = 0; i < sheet.getLastRowNum(); i++) {
//        	System.out.println(i);
//        	
//            rowData =  sheet.getRow(i);
//            if(i%500 == 0){
//            	System.out.println("当前list集合的大小是"+list.size());
//            	set.add(list);
//            	list= new ArrayList<UserInfo>();
//            }
//            UserInfo userInfo = new UserInfo();
//            for (int j = rowData.getFirstCellNum(); j < rowData.getLastCellNum(); j++) {
//                
//                if (j == rowData.getFirstCellNum()) {
//                    userInfo.setUsername(rowData.getCell(j).toString());
//                } else if(j == 1){
//                	userInfo.setCardNo(rowData.getCell(j).toString());
//                }else{
//                	String amount = rowData.getCell(j).toString();
//                	userInfo.setAmount(amount);
//                	total = total.add(new BigDecimal(amount));
//                }
//            }
//            list.add(userInfo);
//            System.out.println(userInfo);
//            
//        }
//        
//        System.out.println("总交易金额为"+total);
//        System.out.println(set.size());
//        System.out.println(list.size());
//
//    }
//
//
//}