package com.hf.spring.mybatis.service;

import com.hf.spring.mybatis.entity.User;

public interface UserService {
	
	public void save(User user);
	public User findUserByUsername(String username);
	public void delete(User user);
}
