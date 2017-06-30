package com.xhld.authenticationservice.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * InsCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user_auths")
public class SysUserAuth implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int userId;        //用户id
	
	private int proofType;    //认证类型 1-用户名	2-邮箱	3-电话	4-第三方接入
	
	private String proofName;     //认证标识
	
	private String proofValue;       //认证内容
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProofName() {
		return proofName;
	}
	public void setProofName(String proofName) {
		this.proofName = proofName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProofType() {
		return proofType;
	}
	public void setProofType(int proofType) {
		this.proofType = proofType;
	}
	public String getProofValue() {
		return proofValue;
	}
	public void setProofValue(String proofValue) {
		this.proofValue = proofValue;
	}
	
}