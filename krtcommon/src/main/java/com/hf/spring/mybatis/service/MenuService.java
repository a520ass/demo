package com.hf.spring.mybatis.service;

import java.util.List;

import com.hf.spring.mybatis.entity.Menu;

public interface MenuService {
	
	List<Menu> getAllMenu();
	Menu getMenu(Integer id);
	void updateMenu(Menu menu);
	void deleteMenu(Integer id);
	int deleteMenuCheck(Integer id);
}
