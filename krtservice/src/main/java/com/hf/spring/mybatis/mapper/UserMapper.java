package com.hf.spring.mybatis.mapper;

import java.util.List;

import com.hf.spring.mybatis.MyBatisDao;
import com.hf.spring.mybatis.entity.User;

@MyBatisDao
public interface UserMapper {
	public List<User> findAll();
	public User findByUsername(String username);
}
