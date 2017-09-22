package com.study.reflect.CodeGener.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.reflect.CodeGener.service.TableInfoService;

@Controller
public class GenInfoController {
	
	@Autowired
	private TableInfoService tableService;
	/**
	 * 查询数据库所有表名
	 */
	@RequestMapping("/queryTableNames")
	public String queryTableName(Model model){
		List<String> list = tableService.queryTableInfo();
		System.out.println("查询结果集为"+list);
		model.addAttribute("list", list);
		return "tableList";
	}

}
