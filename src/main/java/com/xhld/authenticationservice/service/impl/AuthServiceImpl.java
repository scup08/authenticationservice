package com.xhld.authenticationservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzh.common.model.entity.auth.UserDto;
import com.lzh.common.util.JwtTokenUtil;
import com.xhld.authenticationservice.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

//	@Autowired
//    private AuthenticationManager authenticationManager;
    
    
	@Autowired
    private JwtTokenUtil jwtTokenUtil;

    
    

    
    public AuthServiceImpl() {
    }

    @Override
    public UserDto register(UserDto userToAdd) {
//        final String username = userToAdd.getUsername();
//        if(userMapper.findByUserName(username)!=null) {
//            return null;
//        }
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        final String rawPassword = userToAdd.getPassword();
//        userToAdd.setPassword(encoder.encode(rawPassword));
//        userToAdd.setLastPasswordResetDate(new Date());
////        userToAdd.setRoles(asList("ROLE_USER"));
//        
//        //保存用户信息
//        SysUser sysUser = new SysUser();
////        sysUser.setEmail(email); 
////        sysUser.setId(id);
////        sysUser.setIdNo(idNo);
//        sysUser.setLoginName(userToAdd.getUsername());
//        sysUser.setStatus(1);  // 
////        sysUser.setTelephoneNo(telephoneNo);
//        sysUser.setUserName(userToAdd.getUsername());
////        sysUser.setUserType(0);   //普通用户
//        sysUser = sysUserDao.save(sysUser);
//        //保存认证信息
//        SysUserAuth sysUserAuth = new SysUserAuth();
//        sysUserAuth.setProofName(sysUser.getLoginName());
//        sysUserAuth.setProofType(1);
//        sysUserAuth.setProofValue(userToAdd.getPassword());
//        sysUserAuth.setUserId(sysUser.getId());
//        sysUserAuthDao.save(sysUserAuth);
//        //保存用户角色信息
//        SysUserRole sysUserRole = new SysUserRole();
//        sysUserRole.setRoleId(1);    //  默认角色是Base
//        sysUserRole.setUserId(sysUser.getId());
//        sysUserRoleDao.save(sysUserRole);
//        
//        
//        userToAdd.setId(sysUser.getId());
        
        return userToAdd;
        
//        return userRepository.insert(userToAdd);
    }

    @Override
    public String login(String username, String password) {
//        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
//        final Authentication authentication = authenticationManager.authenticate(upToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
    	UserDto userDetails = new UserDto();
    	userDetails.setId(1);
    	userDetails.setUsername(username);
    	userDetails.setPassword(password);
//    	userMapper.findByUserName(username);
        String token = null;
        if(userDetails != null){
        	token = jwtTokenUtil.generateToken(userDetails);
        }
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(jwtTokenUtil.getTokenHead().length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
//        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
//        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
//        }
//        return null;
    }
}
