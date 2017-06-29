package com.xhld.authenticationservice.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * InsCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String loginName;    //登录名
	
	private String userName;     //用户昵称
	
	private int userType;       //用户类型     1-管理员2-领导层3-财务审计
	
	private String telephoneNo; //手机号
	
	private String idNo;        //身份证号 
	
	private String email;       //邮箱
	
	private String status;      //用户状态  0-未激活   1-正常	2-停用
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}