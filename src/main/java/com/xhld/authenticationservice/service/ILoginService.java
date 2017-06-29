package com.xhld.authenticationservice.service;

import java.util.Map;

import com.xhld.authenticationservice.model.entity.SysUser;

/**
 * 
 */
public interface ILoginService {
	/**
	 * 查询用户信息
	 */
	public SysUser findByUsername(String aa);
	
	/**
	 * 查询用户认证信息
	 * @param map
	 * @return
	 */
	public SysUser getAuthInfo(Map map);
	
	/**
	 * 将用户信息存入redis
	 * @param sysUser
	 */
	public String saveUserToRedis(SysUser sysUser);
}