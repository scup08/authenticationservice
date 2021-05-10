/**
 * @author 51937
 * @date 2021年4月20日
 * @version V1.0
 */
package com.lzh.authenticationservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzh.authenticationservice.mapper.UserMapper;
import com.lzh.authenticationservice.model.dto.UserDto;
import com.lzh.authenticationservice.model.entity.UserEntity;
import com.lzh.authenticationservice.service.IUserService;

/**
 * @author 51937
 * @date 2021年4月20日
 */
@Service
public class UserServiceImpl implements UserDetailsService,IUserService {
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Map queryMap = new HashMap(8);
		queryMap.put("username", username);
		UserEntity user = userMapper.loadUserByUsername(queryMap);

		if (user == null ) {
			throw new UsernameNotFoundException("用户不存在");
		}

		return user;
	}

	@Override
	public UserDto queryUser(UserDto userDto) {
		
		UserDto rs = new UserDto();
		
		if(userDto.getIsPaging()) {
			PageHelper.startPage(userDto.getPageNum(),userDto.getPageSize());
		}
		
		Map queryMap = new HashMap(8);
		
		List<UserDto> userList = userMapper.queryUser(queryMap);
		
		if(userDto.getIsPaging()) {
			PageInfo<UserDto> pageInfo = new PageInfo<UserDto>(userList);
			rs.setTotal(pageInfo.getTotal());
		}
		
		rs.setUserList(userList);
		return rs;
		
	}

	@Override
	public void addOrUpdateUser(UserDto UserDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delUser(UserDto UserDto) {
		// TODO Auto-generated method stub
		
	}

}
