package com.hf.spring.mybatis.service;

import com.hf.spring.mybatis.entity.User;

public interface UserService {
	
	
	public User findUserByUsername(String username);
}
