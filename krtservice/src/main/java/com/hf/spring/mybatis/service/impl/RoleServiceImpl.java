package com.hf.spring.mybatis.service.impl;

import com.google.common.collect.Lists;
import com.hf.spring.mybatis.entity.Role;
import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.mapper.BatchDao;
import com.hf.spring.mybatis.mapper.MenuMapper;
import com.hf.spring.mybatis.mapper.RoleMapper;
import com.hf.spring.mybatis.mapper.UserMapper;
import com.hf.spring.mybatis.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired UserMapper userMapper;
	@Autowired MenuMapper menuMapper;
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
	public void updateRole(Role role) {
		if(role.getId()==null){
			roleMapper.insert(role);
		}else{
			roleMapper.updateByPrimaryKey(role);
		}
		
	}

	@Override
	public void deleteRole(Integer id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<User> getUsersByRoleId(List<Integer> ids) {
		List<Integer> userIds = userMapper.selectUserId(ids);
		if(userIds.size()>0){
			return userMapper.selectAllIn(userIds);
		}
		return new ArrayList<User>();
	}

	@Override
	public void allocatedusersave(Long roleId, Long[] userids) {
		userMapper.deleteByRoleId(roleId);
		if(userids!=null){
			batchDao.assign(roleId, userids);
		}
		
	}

	@Override
	public List<Integer> getMenuIdsByRoleId(List<Integer> ids) {
		return roleMapper.selectMenuId(ids);
	}

	@Override
	public void allocatedmenusave(Long roleId, Long[] menuids) {
		menuMapper.deleteByRoleId(roleId);
		if(menuids!=null){
			batchDao.assignMenu(roleId, menuids);
		}
		
	}
	
	/**
	 * 0 可以删除
	 * 1 已和用户关联
	 * 2 已和菜单关联
	 * 3 同时与用户和菜单关联
	 */
	@Override
	public int deleteRoleCheck(Integer id) {
		List<Integer> ids= Lists.newArrayList();
		ids.add(id);
		if(userMapper.selectUserId(ids).size()>0&&roleMapper.selectMenuId(ids).size()>0){
			return 3;
		}
		if(userMapper.selectUserId(ids).size()>0){
			return 1;
		}
		if(roleMapper.selectMenuId(ids).size()>0){
			return 2;
		}
		return 0;
	}
}
