package com.study.dubbo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.dubbo.entity.pojo.User;

public interface UserDao {
	int deleteByPrimaryKey(Integer id);  
	  
    int insert(User record);  
  
    int insertSelective(User record);  
  
    User selectByPrimaryKey(@Param("id") Integer id,@Param("Name") String name);  
  
    int updateByPrimaryKeySelective(User record);  
  
    int updateByPrimaryKey(User record);  
  
    List<User> selectAll();
}
