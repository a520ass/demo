package com.hf.spring.mybatis.web;


import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
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
import com.hf.spring.mybatis.common.utils.UserUtils;
import com.hf.spring.mybatis.entity.Menu;
import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.service.UserService;

@Controller
public class SystemController {
	private static final Logger log=LoggerFactory.getLogger(SystemController.class);
	
	@Autowired
	private UserService userService;
	@Autowired CacheManager cacheManager;
	
	@RequiresRoles(value = { "user" })
	@RequestMapping(value="/sys/f")
	public String index(Model model){
		model.addAttribute("user", UserUtils.getUser());
		return "pages/index";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login() {
		return "pages/login";
	}
	
	/**
	 * 登陆失败的处理
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		model.addAttribute("message", userName + " 登陆失败，请重试");
		return "pages/login";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.GET)
	public String register() {
		return "pages/register";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String registerSave(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		UserUtils.encryptPassword(user);
		userService.updateUser(user);
		redirectAttributes.addFlashAttribute("message", "创建用户" + user.getUsername() + "成功，请登陆");
		
		return "redirect:/login";
	}
	
	@RequiresRoles(value = { "user" })
	@RequestMapping(value="/menu/navigation/get", method = RequestMethod.GET)
	@ResponseBody
	public  Menu getMenuForNavigation() {
		return UserUtils.getMenuForN();
	}
	
	
}

