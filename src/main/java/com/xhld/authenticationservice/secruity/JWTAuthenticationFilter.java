package com.xhld.authenticationservice.secruity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import com.xhld.authenticationservice.util.JwtTokenUtil;

public class JWTAuthenticationFilter extends GenericFilterBean {
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
	@Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
//        Authentication authentication = JwtTokenUtil.getAuthentication((HttpServletRequest)request);
		String authHeader = ((HttpServletRequest) request).getHeader(this.tokenHeader);
        if(authHeader != null){
        	//throw new AuthenticationServiceException("Authorization header cannot be blank!");
        
			String authToken = authHeader.substring(tokenHead.length());
	        String username = jwtTokenUtil.getUsernameFromToken(authToken);
	        
	        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	        Authentication authentication = new UsernamePasswordAuthenticationToken(
	                userDetails, "123", userDetails.getAuthorities());
	        
	        SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }
}
