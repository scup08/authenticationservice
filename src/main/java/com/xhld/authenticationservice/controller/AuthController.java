package com.xhld.authenticationservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xhld.authenticationservice.model.UserDto;
import com.xhld.authenticationservice.service.IAuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private IAuthService authService;

//    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public UserDto createAuthenticationToken(
    		@QueryParam("") UserDto userDto,HttpServletResponse res, HttpServletRequest reqs) throws AuthenticationException{
    	
//    	PublicUtil.setResponse(res);
    	UserDto user = new UserDto();
    	
        final String token = authService.login(userDto.getUsername(), userDto.getPassword());
        user.setToken(token);
        // Return the token
        return user;
    }

//    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public UserDto refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
    	UserDto userDto = new UserDto();
    	
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return null;
        } else {
            return userDto;
        }
    }

    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
//    public UserDto register(@RequestBody UserDto addedUser) throws AuthenticationException{   //接受不到参数，会报异常
    public UserDto register(@QueryParam("") UserDto addedUser) throws AuthenticationException{  //可以接受到
//    public UserDto register(String username ,String password) throws AuthenticationException{  //可以接受到
//    public UserDto register( UserDto addedUser) throws AuthenticationException{   //可以接受到
//    	UserDto addedUser = new UserDto();
        return authService.register(addedUser);
    }
    
    /*
     * 可以删除
     */
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public UserDto test(
//            HttpServletRequest request) throws AuthenticationException{
//    	UserDto userDto = new UserDto();
//    	
//        String token = request.getHeader(tokenHeader);
//        String refreshedToken = authService.refresh(token);
//        if(refreshedToken == null) {
//            return null;
//        } else {
//            return userDto;
//        }
//    }
    
//    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
//    public ResponseEntity<?> createAuthenticationToken(
//            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{
//        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//
//        // Return the token
//        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
//    }
//
//    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
//    public ResponseEntity<?> refreshAndGetAuthenticationToken(
//            HttpServletRequest request) throws AuthenticationException{
//        String token = request.getHeader(tokenHeader);
//        String refreshedToken = authService.refresh(token);
//        if(refreshedToken == null) {
//            return ResponseEntity.badRequest().body(null);
//        } else {
//            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
//        }
//    }
}
