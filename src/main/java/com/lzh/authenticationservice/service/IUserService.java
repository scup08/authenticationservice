/**
 * @author 51937
 * @date 2021年4月20日
 * @version V1.0
 */
package com.lzh.authenticationservice.service;

import java.util.List;

import com.lzh.authenticationservice.model.dto.UserDto;

/**
 * @author 51937
 * @date 2021年4月20日
 */
public interface IUserService {
	
	//public UserDto queryUserPaging(UserDto UserDto);
	
	public UserDto queryUser(UserDto UserDto);
	
	public void addOrUpdateUser(UserDto UserDto);
	
	public void delUser(UserDto UserDto);
	
}
