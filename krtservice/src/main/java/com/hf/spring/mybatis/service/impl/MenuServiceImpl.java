package com.hf.spring.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
}
