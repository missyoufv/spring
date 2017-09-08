package com.study.springaop.MultipleSources.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springaop.MultipleSources.commons.annotation.DataSourceChange;
import com.study.springaop.MultipleSources.commons.annotation.LogerInfoPrt;
import com.study.springaop.MultipleSources.dao.SlaveMapper;
import com.study.springaop.MultipleSources.service.SlaveService;



@Service
public class SlaveServiceImpl implements SlaveService {

	@Autowired
	private SlaveMapper slaveMapper;
	
	@Override
	@LogerInfoPrt(loggerPrt=true)
	@DataSourceChange(slave = true)
	public Integer count() {
		return slaveMapper.count();
	}
	
	
	@LogerInfoPrt(loggerPrt=false)
	@Override
	public Integer count2() {
		return slaveMapper.count2();
	}

}
