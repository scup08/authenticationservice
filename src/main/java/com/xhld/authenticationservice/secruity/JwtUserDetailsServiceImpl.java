package com.xhld.authenticationservice.secruity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xhld.authenticationservice.model.JwtUserFactory;
import com.xhld.authenticationservice.model.UserDto;
import com.xhld.authenticationservice.model.entity.SysUser;
import com.xhld.authenticationservice.service.ILoginService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ILoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = loginService.findByUsername(username);

        
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(changeToUserDto(user));
        }
    }
    
    UserDto changeToUserDto(SysUser sysUser){
    	UserDto userDto = new UserDto();
    	userDto.setEmail(sysUser.getEmail());
    	userDto.setId(String.valueOf(sysUser.getId()));
//    	userDto.setLastPasswordResetDate(sysUser.get); 
//    	userDto.setPassword(password);
//    	userDto.setRoles(roles);
    	userDto.setUserName(sysUser.getUserName());
    	
    	return userDto;
    }
}
