package com.study.springcloud.excel.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.UUID;

/**
 * mysql 插入1000w条数
 * @author admin
 *
 */
public class BigDataInsert {
	
	
	public static void main(String[] args) {
		insert(JdbcUtils.getConnection());
	}

	
	public static void insert(Connection conn){
		
		 // 开始时间
        Long begin = new Date().getTime();
        // sql前缀
        String prefix = "INSERT INTO t_teacher (id,t_name,t_password,sex,description,pic_url,school_name,regist_date,remark) VALUES ";
        
        try{
        	 // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
           
            // 比起st，pst会更好些
            PreparedStatement  pst = (PreparedStatement) conn.prepareStatement("");//准备执行语句
            
         // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 10; i++) {
                suffix = new StringBuffer();
                
                // 第j次提交步长
                for (int j = 1; j <= 10000; j++) {
                    // 构建SQL后缀
                    suffix.append("('" + getRandomId()+"','"+i*j+"','123456'"+ ",'男'"+",'教师'"+",'www.bbk.com'"+",'XX大学'"+",'"+"2016-08-12 14:43:26"+"','备注'" +"),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
              
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 执行操作
            pst.executeBatch();
            // 提交事务
            conn.commit();
            
            // 头等连接
            pst.close();
            conn.close();
        }catch(Exception ex){
        	ex.printStackTrace();
        }
       
     // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println("10万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
	}
	
	public static String getRandomId(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}
