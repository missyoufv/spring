package com.study.reflect.CodeGener.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.reflect.CodeGener.dao.TableInfoDao;

@Service
public class TableInfoService {
	
	@Autowired
	private TableInfoDao tableInfoDao;
	
	public List<String> queryTableInfo(){
		List<String> list = tableInfoDao.getTableNames();
		if(list == null || list.size() == 0)
			return null;
		return list;
		
	}

}
