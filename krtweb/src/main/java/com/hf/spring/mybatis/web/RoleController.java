package com.hf.spring.mybatis.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

import com.hf.spring.mybatis.common.utils.SysUtils;
import com.hf.spring.mybatis.common.utils.UserUtils;
import com.hf.spring.mybatis.entity.Menu;
import com.hf.spring.mybatis.entity.Role;
import com.hf.spring.mybatis.entity.User;
import com.hf.spring.mybatis.service.MenuService;
import com.hf.spring.mybatis.service.RoleService;
import com.hf.spring.mybatis.service.UserService;

/**
 * 角色的Controller.
 */
@Controller
@RequestMapping(value = "/role")
@RequiresPermissions(value = { "sys:role:mgr" })
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired UserService userService;
	@Autowired MenuService menuService;
	
	@RequiresPermissions(value = { "sys:role:view" })
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<Role> roles = roleService.getAllRole();
		model.addAttribute("roles", roles);

		return "pages/roleList";
	}
	
	@RequiresPermissions(value = { "sys:role:edit" })
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("action", "create");
		return "pages/roleForm";
	}
	
	@RequiresPermissions(value = { "sys:role:edit" })
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Role role, RedirectAttributes redirectAttributes) {
		roleService.updateRole(role);
		redirectAttributes.addFlashAttribute("message", "创建角色成功");
		return "redirect:/role";
	}
	
	@RequiresPermissions(value = { "sys:role:edit" })
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		Role role = roleService.getRole(id);
		model.addAttribute("role", role);
		model.addAttribute("action", "update");
		return "pages/roleForm";
	}
	
	@RequiresPermissions(value = { "sys:role:edit" })
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("role") Role role, RedirectAttributes redirectAttributes) {
		
		roleService.updateRole(role);
		redirectAttributes.addFlashAttribute("message", "更新角色类型" + role.getRoleType() + "成功");
		return "redirect:/role";
	}
	
	@RequiresPermissions(value = { "sys:role:delete" })
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		/*User user = userService.getUser(id);
		if(SecurityUtils.getSubject().getPrincipal().equals(user.getUsername())){
			redirectAttributes.addFlashAttribute("message", "不能删掉当前登陆的用户" + user.getUsername());
		}else{
			userService.deleteUser(id);
			redirectAttributes.addFlashAttribute("message", "删除用户" + user.getUsername() + "成功");
		}*/
		redirectAttributes.addFlashAttribute("message", "当前版本未实现");
		
		return "redirect:/role";
	}
	
	@RequiresPermissions(value = { "sys:role:mgr" })
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
	
	@RequiresPermissions(value = { "sys:role:mgr" })
	@RequestMapping(value = "allocatedusersave",method=RequestMethod.POST)
	@ResponseBody
	public boolean allocatedUserSave(@RequestParam("roleId")Long roleId,@RequestParam(value="userids[]",required=false)Long[] userids,Model model, RedirectAttributes redirectAttributes) {
		boolean flag=false;
		try {
			roleService.allocatedusersave(roleId, userids);
			UserUtils.removeCache(UserUtils.CACHE_MENU_N);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	@RequiresPermissions(value = { "sys:role:mgr" })
	@RequestMapping(value = "allocatedmenu/{id}")
	public String allocatedMenu(@PathVariable("id") Integer id,Model model, RedirectAttributes redirectAttributes) {
		Role role = roleService.getRole(id);
		model.addAttribute("role", role);
		return "pages/roleAllocatedMenu";
	}
	
	@RequiresPermissions(value = { "sys:role:mgr" })
	@RequestMapping(value = "allocatedmenusave",method=RequestMethod.POST)
	@ResponseBody
	public boolean allocatedMenuSave(@RequestParam("roleId")Long roleId,@RequestParam(value="menuids[]",required=false)Long[] menuids,Model model, RedirectAttributes redirectAttributes) {
		boolean flag=false;
		try {
			roleService.allocatedmenusave(roleId, menuids);
			UserUtils.removeCache(UserUtils.CACHE_MENU_N);
			SysUtils.getSpringCache(SysUtils.SYS_CACHE).evict(SysUtils.CACHE_MENU_LIST_R+roleId);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	@RequiresPermissions(value = { "sys:role:mgr" })
	@RequestMapping(value="/menu/get/{roleid}", method = RequestMethod.GET)
	@ResponseBody
	public  List<Menu> getMenu(@PathVariable("roleid") Integer id) {
		return SysUtils.getMenuListForR(id);
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
