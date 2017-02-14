package com.hf.spring.mybatis.service;

import java.util.List;

import com.hf.spring.mybatis.entity.Role;
import com.hf.spring.mybatis.entity.User;

public interface RoleService {
	
	List<Role> getAllRole();
	Role getRole(Integer id);
	void updateRole(Role user);
	void deleteRole(Integer id);
	
	List<User> getUsersByRoleId(List<Integer> ids);
	void allocatedusersave(Long roleId, Long[] userids);
	
	List<Integer> getMenuIdsByRoleId(List<Integer> ids);
	void allocatedmenusave(Long roleId, Long[] menuids);
}
