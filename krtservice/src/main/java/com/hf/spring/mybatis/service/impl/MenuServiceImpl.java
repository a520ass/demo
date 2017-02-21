package com.hf.spring.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hf.spring.mybatis.entity.Menu;
import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.mapper.MenuMapper;
import com.hf.spring.mybatis.mapper.UserMapper;
import com.hf.spring.mybatis.service.MenuService;
import com.hf.spring.mybatis.service.UserService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService{
	
	@Autowired MenuMapper menuMapper;
	
	@Override
	public List<Menu> getAllMenu() {
		return menuMapper.selectAll();
	}

	@Override
	public Menu getMenu(Integer id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateMenu(Menu menu) {
		if (menu.getId()==null) {
			menuMapper.insert(menu);
		}else{
			menuMapper.updateByPrimaryKey(menu);
		}
		
	}

	@Override
	public void deleteMenu(Integer id) {
		menuMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 0 可以删除
	 * 1 与角色关联
	 * 2 有下级菜单
	 * 3 与角色关联 同时有下级菜单
	 */
	@Override
	public int deleteMenuCheck(Integer id) {
		List<Integer> ids=Lists.newArrayList();
		ids.add(id);
		if(menuMapper.selectRoleId(ids).size()>0&&menuMapper.selectByPId(id)!=null){
			return 3;
		}
		if(menuMapper.selectRoleId(ids).size()>0){
			return 1;
		}
		if(menuMapper.selectByPId(id)!=null){
			return 2;
		}
		return 0;
	}
	
}
