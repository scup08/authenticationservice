package com.xhld.authenticationservice.secruity;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * An {@link AuthenticationProvider} implementation that will use provided
 * instance of {@link JwtToken} to perform authentication.
 * 
 * @author vladimir.stankovic
 *
 * Aug 5, 2016
 */
//@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
//    private final JwtSettings jwtSettings;
    
//    @Autowired
//    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
//        this.jwtSettings = jwtSettings;
//    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
//    	UsernamePasswordAuthenticationToken 
//        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
//        String subject = jwsClaims.getBody().getSubject();
//        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
//        List<GrantedAuthority> authorities = scopes.stream()
//                .map(authority -> new SimpleGrantedAuthority(authority))
//                .collect(Collectors.toList());
        List<GrantedAuthority> authorities = null;
//        UserContext context = UserContext.create(subject, authorities);
        
//        return new JwtAuthenticationToken(context, context.getAuthorities());
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
