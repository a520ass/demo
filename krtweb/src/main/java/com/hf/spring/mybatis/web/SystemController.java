package com.hf.spring.mybatis.web;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hf.spring.mybatis.common.realm.SystemAuthorizingRealm;
import com.hf.spring.mybatis.common.utils.MenuTree;
import com.hf.spring.mybatis.entity.Menu;
import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.service.UserService;

@Controller
public class SystemController {
	private static final Logger log=LoggerFactory.getLogger(SystemController.class);
	
	@Autowired
	private UserService userService;
	
	@RequiresRoles(value = { "user" })
	@RequestMapping(value="/sys/f")
	public String index(Model model){
		Object principal = SecurityUtils.getSubject().getPrincipal();
		User user = userService.findUserByUsername(principal.toString());
		model.addAttribute("user", user);
		return "pages/index";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login() {
		return "pages/login";
	}

	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "pages/login";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.GET)
	public String register() {
		return "pages/register";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String registerSave(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		SystemAuthorizingRealm.encrypt(user);
		userService.updateUser(user);
		redirectAttributes.addFlashAttribute("message", "创建用户" + user.getUsername() + "成功，请登陆");
		
		return "redirect:/login";
	}
	
	@RequiresRoles(value = { "user" })
	@RequestMapping(value="/menu/navigation/get", method = RequestMethod.GET)
	@ResponseBody
	public  Menu getMenuForNavigation() {
		Object principal = SecurityUtils.getSubject().getPrincipal();
		User user = userService.findUserByUsername(principal.toString());
		List<Menu> menus = userService.getCurrentMenu(user.getId());
		//List<Menu> list = new ArrayList<Menu>();
		//MenuController.sortList(list, menus, 0, true);//排序
		List<Menu> menusForN = MenuTree.wrapMenuForNavigation(menus);
		MenuTree menuTree = new MenuTree(menusForN);
		try {
			
			Menu menu = menuTree.recursiveTree(1);//构建数结构
			menuTree.sortMenuForNavigation(menu);
			return menu;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}

