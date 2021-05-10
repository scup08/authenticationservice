/**
 * 
 */
package com.lzh.authenticationservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzh.authenticationservice.model.RestResponse;
import com.lzh.authenticationservice.model.dto.UserDto;
import com.lzh.authenticationservice.service.IUserService;

/**
 * @author 51937
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserService userService;

	@GetMapping("")
	public RestResponse getUsers() {
		UserDto ud = new UserDto();
		ud.setIsPaging(true);
		UserDto rsUser = userService.queryUser(ud);
		
		return new RestResponse().success("查询成功", rsUser);
	}
	
	@GetMapping("/{userId}")
	public String getUser(@PathVariable(value = "userId") Long userId) {
		logger.info("查询用户id： " + userId);
		return "heeeeello ";
	}
	
	@PutMapping("/{userId}")
	public String updatetUser(@PathVariable(value = "userId") Long userId) {
		logger.info("更新用户id： " + userId);
		return "heeeeello ";
	}
	
	@PostMapping("")
	public String insertUser() {
		logger.info("新增用户 ");
		return "heeeeello ";
	}
	
	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable(value = "userId") Long userId) {
		logger.info("删除用户id： " + userId);
		return "heeeeello ";
	}

}

