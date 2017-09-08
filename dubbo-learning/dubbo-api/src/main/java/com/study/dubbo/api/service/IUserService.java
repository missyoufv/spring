package com.study.dubbo.api.service;

import java.util.List;

import com.study.dubbo.entity.pojo.User;

public interface IUserService {
	
	List<User> getUsers();
	User getUserByPrimaryKey(String id);

}
