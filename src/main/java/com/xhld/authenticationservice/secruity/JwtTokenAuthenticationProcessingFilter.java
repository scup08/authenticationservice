package com.xhld.authenticationservice.secruity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.xhld.authenticationservice.model.JwtUserFactory;
import com.xhld.authenticationservice.model.UserDto;
import com.xhld.authenticationservice.util.JwtTokenUtil;

/**
 * Performs validation of provided JWT Token.
 * 
 * @author vladimir.stankovic
 *
 * Aug 5, 2016
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private final AuthenticationFailureHandler failureHandler;
//    private final TokenExtractor tokenExtractor;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    
//    @Autowired
    public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler, 
    		RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
//        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
    	UsernamePasswordAuthenticationToken authentication = null;
        String authHeader = request.getHeader(this.tokenHeader);
        if(authHeader == null){
        	throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }
        
        final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));    
        
        return getAuthenticationManager().authenticate(authentication);
    }

//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//            Authentication authResult) throws IOException, ServletException {
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(authResult);
//        SecurityContextHolder.setContext(context);
//        chain.doFilter(request, response);
//    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
