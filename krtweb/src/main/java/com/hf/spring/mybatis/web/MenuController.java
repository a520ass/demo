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

import com.hf.spring.mybatis.common.utils.SysUtils;
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
	
	/**
	 * ajax获取数据
	 * @return
	 */
	@RequestMapping(value="/get", method = RequestMethod.GET)
	@ResponseBody
	public  List<Menu> get() {
		return SysUtils.getAllMenuList();
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
		SysUtils.getSpringCache(SysUtils.SYS_CACHE).evict(SysUtils.CACHE_MENU_LIST);
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
		SysUtils.getSpringCache(SysUtils.SYS_CACHE).evict(SysUtils.CACHE_MENU_LIST);
		redirectAttributes.addFlashAttribute("message", "修改菜单成功");
		return "redirect:/menu";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		//menuService.deleteMenu(id);
		SysUtils.getSpringCache(SysUtils.SYS_CACHE).evict(SysUtils.CACHE_MENU_LIST);
		redirectAttributes.addFlashAttribute("message", "当前版本未实现");
		return "redirect:/menu";
	}
	
	
}
