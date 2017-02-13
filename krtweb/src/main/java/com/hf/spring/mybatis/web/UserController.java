package com.hf.spring.mybatis.web;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hf.spring.mybatis.common.realm.SystemAuthorizingRealm;
import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.service.UserService;

/**
 * 用户的Controller.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//@RequiresPermissions(value = { "sys:user:view" })
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<User> users = userService.getAllUser();
		model.addAttribute("users", users);

		return "pages/userList";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		User user = userService.getUser(id);
		user.setPassword(null);
		model.addAttribute("user", user);
		return "pages/userForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		User dbUser = userService.getUser(user.getId());
		dbUser.setUsername(user.getUsername());
		dbUser.setName(user.getName());
		if(!StringUtils.isEmpty(user.getPassword())){
			dbUser.setPassword(user.getPassword());
			SystemAuthorizingRealm.encrypt(dbUser);
		}
		userService.updateUser(dbUser);
		redirectAttributes.addFlashAttribute("message", "更新用户" + user.getUsername() + "成功");
		return "redirect:/user";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		User user = userService.getUser(id);
		if(SecurityUtils.getSubject().getPrincipal().equals(user.getUsername())){
			redirectAttributes.addFlashAttribute("message", "不能删掉当前登陆的用户" + user.getUsername());
		}else{
			userService.deleteUser(id);
			redirectAttributes.addFlashAttribute("message", "删除用户" + user.getUsername() + "成功");
		}
		
		return "redirect:/user";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			model.addAttribute("user", userService.getUser(id));
		}
	}
}
