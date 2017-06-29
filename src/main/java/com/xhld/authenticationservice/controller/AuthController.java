package com.xhld.authenticationservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xhld.authenticationservice.model.UserDto;
import com.xhld.authenticationservice.service.IAuthService;

@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private IAuthService authService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public UserDto createAuthenticationToken(
            @RequestBody UserDto userDto) throws AuthenticationException{
    	UserDto user = new UserDto();
    	
        final String token = authService.login(userDto.getUserName(), userDto.getPassword());

        // Return the token
        return user;
    }

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
    public UserDto register(@RequestBody UserDto addedUser) throws AuthenticationException{
        return authService.register(addedUser);
    }
    
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
