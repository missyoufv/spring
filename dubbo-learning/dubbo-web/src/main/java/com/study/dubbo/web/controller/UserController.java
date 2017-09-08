package com.study.dubbo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.dubbo.api.service.ICacheService;
import com.study.dubbo.api.service.IUserService;
import com.study.dubbo.entity.pojo.User;

@Controller  
@RequestMapping("/user")  
public class UserController {  
      
    @SuppressWarnings("unused")  
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);  
      
    @Autowired
    private IUserService userService;  
    
    @Autowired
    private ICacheService cacheService;
    
    @RequestMapping("/")  
    public String goIndex(){  
        return "index";  
    }  
      
    @RequestMapping("/list")  
    @ResponseBody  
    public List<User> getUsers(){  
        return userService.getUsers();  
    }  
      
    @RequestMapping("/one")  
    @ResponseBody  
    public User getUserById(){  
    	
        User user = userService.getUserByPrimaryKey("1");
        cacheService.put(user.getId(), user);
        return user;
    }  
}  
