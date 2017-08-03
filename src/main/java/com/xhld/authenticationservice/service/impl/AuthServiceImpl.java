package com.xhld.authenticationservice.service.impl;

import static java.util.Arrays.asList;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.xhld.authenticationservice.dao.SysUserAuthDao;
import com.xhld.authenticationservice.dao.SysUserDao;
import com.xhld.authenticationservice.dao.SysUserRoleDao;
import com.xhld.authenticationservice.mapper.UserMapper;
import com.xhld.authenticationservice.model.JwtUser;
import com.xhld.authenticationservice.model.UserDto;
import com.xhld.authenticationservice.model.entity.SysUser;
import com.xhld.authenticationservice.model.entity.SysUserAuth;
import com.xhld.authenticationservice.model.entity.SysUserRole;
import com.xhld.authenticationservice.service.IAuthService;
import com.xhld.authenticationservice.util.JwtTokenUtil;

@Service
public class AuthServiceImpl implements IAuthService {

//	@Autowired
//    private AuthenticationManager authenticationManager;
    
	@Autowired
    private UserDetailsService userDetailsService;
    
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private SysUserDao sysUserDao;
    
    @Autowired
    private SysUserAuthDao sysUserAuthDao;
    
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    
    

    
    public AuthServiceImpl() {
    }

    @Override
    public UserDto register(UserDto userToAdd) {
        final String username = userToAdd.getUsername();
        if(userMapper.findByUserName(username)!=null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
//        userToAdd.setRoles(asList("ROLE_USER"));
        
        //保存用户信息
        SysUser sysUser = new SysUser();
//        sysUser.setEmail(email); 
//        sysUser.setId(id);
//        sysUser.setIdNo(idNo);
        sysUser.setLoginName(userToAdd.getUsername());
        sysUser.setStatus(1);  // 
//        sysUser.setTelephoneNo(telephoneNo);
        sysUser.setUserName(userToAdd.getUsername());
//        sysUser.setUserType(0);   //普通用户
        sysUser = sysUserDao.save(sysUser);
        //保存认证信息
        SysUserAuth sysUserAuth = new SysUserAuth();
        sysUserAuth.setProofName(sysUser.getLoginName());
        sysUserAuth.setProofType(1);
        sysUserAuth.setProofValue(userToAdd.getPassword());
        sysUserAuth.setUserId(sysUser.getId());
        sysUserAuthDao.save(sysUserAuth);
        //保存用户角色信息
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(1);    //  默认角色是Base
        sysUserRole.setUserId(sysUser.getId());
        sysUserRoleDao.save(sysUserRole);
        
        
        userToAdd.setId(sysUser.getId());
        
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
    	UserDto userDetails = userMapper.findByUserName(username);
        String token = null;
        if(userDetails != null){
        	token = jwtTokenUtil.generateToken(userDetails);
        }
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
