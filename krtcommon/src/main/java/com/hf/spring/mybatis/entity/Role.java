package com.hf.spring.mybatis.entity;

import java.io.Serializable;

public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -654329834907279052L;
	private Integer id;
	private String roleNo;
	private String roleName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}
	public Role() {
		super();
	}
	public Role(Integer id, String roleNo, String roleName) {
		super();
		this.id = id;
		this.roleNo = roleNo;
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleNo=" + roleNo + ", roleName=" + roleName + "]";
	}

}
