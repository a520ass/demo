package com.hf.spring.mybatis.service.impl;

import java.util.List;

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
		return userMapper.selectByUsername(username);
	}

	@Override
	public List<User> getAllUser() {
		return userMapper.selectAll();
	}

	@Override
	public User getUser(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void deleteUser(Integer id) {
		userMapper.deleteByPrimaryKey(id);
		
	}
}
