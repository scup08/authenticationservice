package com.xhld.authenticationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xhld.authenticationservice.model.UserDto;
import com.xhld.authenticationservice.service.ILoginService;

/**
 * 在 @PreAuthorize 中我们可以利用内建的 SPEL 表达式：比如 'hasRole()' 来决定哪些用户有权访问。
 * 需注意的一点是 hasRole 表达式认为每个角色名字前都有一个前缀 'ROLE_'。所以这里的 'ADMIN' 其实在
 * 数据库中存储的是 'ROLE_ADMIN' 。这个 @PreAuthorize 可以修饰Controller也可修饰Controller中的方法。
 **/
@RestController
@RequestMapping("/users")
public class UserController {
	
    @Autowired
    private ILoginService loginService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getUsers() {
//        return loginService.findAll();
    	return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    UserDto addUser(@RequestBody UserDto addedUser) {
//        return loginService.insert(addedUser);
    	return null;
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable String id) {
//        return loginService.findOne(id);
    	return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    UserDto updateUser(@PathVariable String id, @RequestBody UserDto updatedUser) {
        updatedUser.setId(id);
//        return loginService.save(updatedUser);
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    UserDto removeUser(@PathVariable String id) {
//    	UserDto deletedUser = loginService.findOne(id);
//    	loginService.delete(id);
//        return deletedUser;
    	return null;
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public UserDto getUserByUsername(@RequestParam(value="username") String username) {
//        return loginService.findByUsername(username);
    	return null;
    }
}
