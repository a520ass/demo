package com.hf.spring.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hf.spring.mybatis.entity.Role;
import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.mapper.BatchDao;
import com.hf.spring.mybatis.mapper.RoleMapper;
import com.hf.spring.mybatis.mapper.UserMapper;
import com.hf.spring.mybatis.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired UserMapper userMapper;
	@Autowired BatchDao batchDao;
	

	@Override
	public List<Role> getAllRole() {
		return roleMapper.selectAll();
	}

	@Override
	public Role getRole(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateRole(Role Role) {
		roleMapper.updateByPrimaryKey(Role);
	}

	@Override
	public void deleteRole(Integer id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<User> getUsersByRoleId(List<Integer> ids) {
		List<Integer> userIds = userMapper.selectUserId(ids);
		return userMapper.selectAllIn(userIds);
	}

	@Override
	public void allocatedusersave(Long roleId, Long[] userids) {
		userMapper.deleteByRoleId(roleId);
		batchDao.assign(roleId, userids);
	}
}
