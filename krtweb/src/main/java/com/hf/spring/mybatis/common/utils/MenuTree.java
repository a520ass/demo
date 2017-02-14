package com.hf.spring.mybatis.common.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.hf.spring.mybatis.entity.Menu;

public class MenuTree {

	private List<Menu> menuList = new ArrayList<Menu>();

	public MenuTree(List<Menu> list) {
		menuList = list;
	}

	/**
	 * 
	 * @param nodeId
	 * @return
	 */
	public Menu getNodeById(int nodeId) {
		Menu Menu = new Menu();
		for (Menu item : menuList) {
			if (item.getId() == nodeId) {
				Menu = item;
				break;
			}
		}
		return Menu;
	}

	/**
	 * 
	 * @param nodeId
	 * @return
	 */
	public List<Menu> getChildrenNodeById(int nodeId) {
		List<Menu> childrenMenu = new ArrayList<Menu>();
		for (Menu item : menuList) {
			if (Integer.valueOf(item.getParentId()) == nodeId) {
				childrenMenu.add(item);
			}
		}
		return childrenMenu;
	}

	/**
	 * 递归算法解析成树形结构
	 *
	 * @param cid
	 * @return
	 */
	public Menu recursiveTree(int cid) {
		Menu node = this.getNodeById(cid);
		List<Menu> childMenus = this.getChildrenNodeById(cid);
		// 遍历子节点
		for (Menu child : childMenus) {
			Menu n = this.recursiveTree(child.getId()); // 递归
			node.getChildMenus().add(n);
		}
		return node;
	}

	/**
	 * 递归排序
	 * 
	 * @param menu
	 */
	public void sortMenuForNavigation(Menu menu) {
		List<Menu> menus = menu.getChildMenus();
		menus.sort(new Comparator<Menu>() {
			@Override
			public int compare(Menu o1, Menu o2) {
				return o1.getSort() - o2.getSort();
			}
		});
		for (Menu menu2 : menus) {
			if (menu2.getChildMenus().size() > 1) {//当下级菜单个数大于1时，才继续排序
				this.sortMenuForNavigation(menu2);
			}
		}

	}
	
	public static List<Menu> wrapMenuForNavigation(List<Menu> srcmenus) {
		List<Menu> targetmenus = new ArrayList<>();
		for (int i = 0; i < srcmenus.size(); i++) {
			if(srcmenus.get(i).getIsShow()=='0'){
				targetmenus.add(srcmenus.get(i));
			}
		}
		return targetmenus;

	}
}
