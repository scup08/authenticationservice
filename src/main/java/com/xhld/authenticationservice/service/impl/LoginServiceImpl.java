package com.xhld.authenticationservice.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhld.authenticationservice.dao.UserRedisDao;
import com.xhld.authenticationservice.mapper.UserMapper;
import com.xhld.authenticationservice.model.UserDto;
import com.xhld.authenticationservice.model.entity.SysUser;
import com.xhld.authenticationservice.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRedisDao userRedisDao;
	
	@Override
    public UserDto findByUsername(String name) {
		return userMapper.findByUserName(name);
	}

	@Override
	public SysUser getAuthInfo(Map map) {
		return userMapper.getAuthInfo(map);
	}

	@Override
	public String saveUserToRedis(SysUser sysUser) {
		//生成token
		String token = "";
		
		
		
		userRedisDao.save(token, sysUser);
		
		return token;
	}
    
	
}