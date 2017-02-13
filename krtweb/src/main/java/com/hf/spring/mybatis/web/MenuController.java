package com.hf.spring.mybatis.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hf.spring.mybatis.entity.Menu;
import com.hf.spring.mybatis.entity.Role;
import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired MenuService menuService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		return "pages/menuList";
	}
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
	@ResponseBody
	public  List<Menu> get() {
		List<Menu> menus = menuService.getAllMenu();
		ArrayList<Menu> list = new ArrayList<Menu>();
		sortList(list, menus, 0, true);
		return list;
	}
	
	@RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
	public String createForm(@PathVariable("id") Integer id,Model model) {//传入上级菜单的id
		Menu parentMenu = menuService.getMenu(id);
		Menu menu = new Menu();
		menu.setParentId(String.valueOf(parentMenu.getId()));
		menu.setParentIds(parentMenu.getParentIds()+parentMenu.getId());
		Map<String, String> isShowDict=new HashMap<>();
		isShowDict.put("0", "显示");
		isShowDict.put("1", "隐藏");
		menu.setIsShowDict(isShowDict);
		model.addAttribute("menu", menu);
		model.addAttribute("action", "create");
		return "pages/menuForm";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Menu menu, RedirectAttributes redirectAttributes) {
		menuService.updateMenu(menu);
		redirectAttributes.addFlashAttribute("message", "创建菜单成功");
		return "redirect:/menu";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id,Model model) {
		Menu menu = menuService.getMenu(id);
		
		Map<String, String> isShowDict=new HashMap<>();
		isShowDict.put("0", "显示");
		isShowDict.put("1", "隐藏");
		menu.setIsShowDict(isShowDict);
		model.addAttribute("menu", menu);
		model.addAttribute("action", "update");
		return "pages/menuForm";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid Menu menu, RedirectAttributes redirectAttributes) {
		menuService.updateMenu(menu);
		redirectAttributes.addFlashAttribute("message", "修改菜单成功");
		return "redirect:/menu";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		//menuService.deleteMenu(id);
		redirectAttributes.addFlashAttribute("message", "当前版本未实现");
		return "redirect:/menu";
	}
	
	public static void sortList(List<Menu> list, List<Menu> sourcelist, Integer parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			Menu e = sourcelist.get(i);
			if ( e.getParentId()!=null
					&& e.getParentId().equals(String.valueOf(parentId))){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						Menu child = sourcelist.get(j);
						if (child.getParentId()!=null
								&& child.getParentId().equals(String.valueOf(e.getId()))){
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param currentMenuIds 当前角色包含的菜单id集合
	 * @param menus 菜单集合
	 */
	public static void wrapZtreeData(List<Integer> currentMenuIds, List<Menu> menus){
		for (int i = 0; i < currentMenuIds.size(); i++) {
			for (int j = 0; j < menus.size(); j++) {
				if(currentMenuIds.get(i).intValue()==menus.get(j).getId().intValue()){
					menus.get(j).setChecked(true);
					break;
				}
			}
		}
	}
}
