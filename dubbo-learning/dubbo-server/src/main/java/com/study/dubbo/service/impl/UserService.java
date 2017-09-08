package com.study.dubbo.service.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.JSON;
import com.study.dubbo.api.service.IUserService;
import com.study.dubbo.dao.UserDao;
import com.study.dubbo.entity.pojo.User;

@Service(value="userService")
public class UserService implements IUserService{

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);  
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getUsers() {
		logger.info("开始查询所有用户信息");  
        
        List<User> listUser = userDao.selectAll(); 
        try {  
            logger.info("查询结果：" + JSON.json(listUser));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        logger.info("查询结束");  
        return listUser;  
	}

	@Override
	public User getUserByPrimaryKey(String id) {
		
        logger.info("开始查询用户信息，查询条件ID为:"+id);  
        User user = userDao.selectByPrimaryKey(Integer.valueOf(id),"张三");
        logger.info("查询结果：" + user.toString());  
        return user;  
	}

}
