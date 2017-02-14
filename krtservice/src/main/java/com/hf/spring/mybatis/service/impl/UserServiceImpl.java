package com.hf.spring.mybatis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hf.spring.mybatis.entity.Menu;
import com.hf.spring.mybatis.entity.Role;
import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.mapper.MenuMapper;
import com.hf.spring.mybatis.mapper.RoleMapper;
import com.hf.spring.mybatis.mapper.UserMapper;
import com.hf.spring.mybatis.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserMapper userMapper;
	@Autowired RoleMapper roleMapper;
	@Autowired MenuMapper menuMapper;
	
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
		if(user.getId()==null){
			//注册时设置注册时间
			user.setCreateDate(new Date());
			userMapper.insert(user);
		}else{
			userMapper.updateByPrimaryKey(user);
		}
		
	}

	@Override
	public void deleteUser(Integer id) {
		userMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void updateLastLoginDate(String username) {
		userMapper.updateLastLoginDate(username,new Date());
	}

	@Override
	public List<Menu> getCurrentMenu(Integer id) {
		List<Integer> ids = new ArrayList<>();
		ids.add(id);
		List<Integer> roleIds = userMapper.selectRoleId(ids);
		if(roleIds!=null&&roleIds.size()>0){
			List<Integer> allmenuIds = roleMapper.selectMenuId(roleIds);
			Set<Integer> menuIds = new HashSet<>(allmenuIds);//简单去重
			if(allmenuIds!=null&&allmenuIds.size()>0){
				return menuMapper.selectAllIn(new ArrayList<>(menuIds));
			}
			
		}
		return new ArrayList<>();
	}
}
