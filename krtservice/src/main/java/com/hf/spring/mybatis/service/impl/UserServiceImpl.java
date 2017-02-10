package com.hf.spring.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.mapper.UserMapper;
import com.hf.spring.mybatis.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findUserByUsername(String username){
		return userMapper.findByUsername(username);
	}

	@Override
	public void save(User user) {
		userMapper.save(user);
	}

	@Override
	public void delete(User user) {
		userMapper.delete(user);
	}
}
