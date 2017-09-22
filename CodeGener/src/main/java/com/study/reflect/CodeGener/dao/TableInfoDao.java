package com.study.reflect.CodeGener.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.study.reflect.CodeGener.utils.JdbcUtils;

@Repository
public class TableInfoDao {
	
	public List<String> getTableNames(){
		
		List<String> list = new ArrayList<String>();
		Connection conn = JdbcUtils.getConnection();
		String sql = "select table_name from information_schema.tables where table_schema='trs'";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
