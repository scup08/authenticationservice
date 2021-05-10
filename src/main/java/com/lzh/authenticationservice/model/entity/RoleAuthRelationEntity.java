package com.lzh.authenticationservice.model.entity;

public class RoleAuthRelationEntity {
	private Long rId;
	private Long roleId;
	private Long authId;
	public Long getrId() {
		return rId;
	}
	public void setrId(Long rId) {
		this.rId = rId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getAuthId() {
		return authId;
	}
	public void setAuthId(Long authId) {
		this.authId = authId;
	}
	
}
