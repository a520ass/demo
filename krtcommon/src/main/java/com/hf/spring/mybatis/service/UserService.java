package com.hf.spring.mybatis.service;

import java.util.List;

import com.hf.spring.mybatis.entity.User;

public interface UserService {
	
	public User findUserByUsername(String username);
	List<User> getAllUser();
	User getUser(Integer id);
	void updateUser(User user);
	void deleteUser(Integer id);
}
