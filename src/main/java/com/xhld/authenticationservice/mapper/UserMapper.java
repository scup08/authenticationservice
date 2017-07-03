package com.xhld.authenticationservice.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xhld.authenticationservice.model.UserDto;
import com.xhld.authenticationservice.model.entity.SysUser;

@Repository
public interface UserMapper {

    
    public UserDto findByUserName(String username);

	public SysUser getAuthInfo(Map map);
	
}