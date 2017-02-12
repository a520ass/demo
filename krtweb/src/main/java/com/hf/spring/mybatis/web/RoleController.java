package com.hf.spring.mybatis.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

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

import com.hf.spring.mybatis.entity.Role;
import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.service.RoleService;
import com.hf.spring.mybatis.service.UserService;

/**
 * 角色的Controller.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<Role> roles = roleService.getAllRole();
		model.addAttribute("roles", roles);

		return "pages/roleList";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		Role role = roleService.getRole(id);
		model.addAttribute("role", role);
		return "pages/roleForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("role") Role role, RedirectAttributes redirectAttributes) {
		
		roleService.updateRole(role);
		redirectAttributes.addFlashAttribute("message", "更新角色类型" + role.getRoleType() + "成功");
		return "redirect:/role";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		/*User user = userService.getUser(id);
		if(SecurityUtils.getSubject().getPrincipal().equals(user.getUsername())){
			redirectAttributes.addFlashAttribute("message", "不能删掉当前登陆的用户" + user.getUsername());
		}else{
			userService.deleteUser(id);
			redirectAttributes.addFlashAttribute("message", "删除用户" + user.getUsername() + "成功");
		}*/
		
		return "redirect:/role";
	}
	
	@RequestMapping(value = "allocateduser/{id}")
	public String allocatedUser(@PathVariable("id") Integer id,Model model, RedirectAttributes redirectAttributes) {
		Role role = roleService.getRole(id);
		//List<Position> list = positionService.getPositionPage(null, new HashMap<String, Object>(), 1, Integer.MAX_VALUE, "auto").getContent();
		List<User> users = userService.getAllUser();
		List<Integer> list = new ArrayList<Integer>();
		list.add(id);
		List<User> alreadyAllocatedUsers=roleService.getUsersByRoleId(list);
		model.addAttribute("role", role);
		model.addAttribute("users", users);
		model.addAttribute("alreadyAllocatedUsers", alreadyAllocatedUsers);
		return "pages/roleAllocated";
	}
	
	@RequestMapping(value = "allocatedpositionsave",method=RequestMethod.POST)
	@ResponseBody
	public boolean allocatedPositionSave(@RequestParam("roleId")Long roleId,@RequestParam("userids[]")Long[] userids,Model model, RedirectAttributes redirectAttributes) {
		boolean flag=false;
		try {
			
			roleService.allocatedusersave(roleId, userids);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		if (id != -1) {
			model.addAttribute("role", roleService.getRole(id));
		}
	}
}
