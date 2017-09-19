package com.study.springcloud.excel.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.study.springcloud.excel.vo.OrderInfo;

public class DataSourceToExcel {
	
	/**
	 * 该demo主要是尝试通过数据库连接 ，获取数据源信息 ，然后通过反射，封装到实体类中
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT CP_ORDER_NO as orderNo,STATUS as status,AMT as amount FROM mp_trade_order limit 0,50";
		try{
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columCount = metaData.getColumnCount(); //查询结果集的列数
			
			ArrayList<String> list = new ArrayList<String>();
			for(int i=0;i<columCount;i++){
				System.out.println(metaData.getColumnLabel(i+1));
				list.add(metaData.getColumnLabel(i+1));
			}
			List<OrderInfo> orderInfolist = new ArrayList<OrderInfo>();
			//封装到实体bean
			while(rs.next()){
				OrderInfo orderInfo = (OrderInfo) fillData(rs,list,OrderInfo.class);
				System.out.println("封装实体属性成功"+orderInfo);
			}
		}catch(Exception ex){
			System.out.println("导出数据异常"+ex);
		}
		
		
	}

	private static Object fillData(ResultSet rs, ArrayList<String> list,
			Class clazz) throws Exception{
		
		Object object = clazz.newInstance();
		for(int i=0;i<list.size();i++){
			String propertyName = list.get(i);
			Field field = clazz.getDeclaredField(propertyName);
			Class type = field.getType();
			String methodName = "set".concat(propertyName.substring(0,1).toUpperCase().concat(propertyName.substring(1)));
			Method method = clazz.getMethod(methodName,type);
			if(method != null){
				method.setAccessible(true);
				method.invoke(object, rs.getString(i+1));
			}
			
		}
		return object;
	}

}
