/**
 * 
 */
package com.lzh.authenticationservice.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author 51937
 *
 */

public class AuthorizationServerbak extends AuthorizationServerConfigurerAdapter {
	
//	@Autowired
//	TokenStore tokenStore;
//	@Autowired
//	ClientDetailsService clientDetailsService;
//	
//	@Autowired
//	AuthenticationManager authenticationManager;
//	
//	@Autowired
//	JwtAccessTokenConverter jwtAccessTokenConverter;
//	@Autowired
//	CustomAdditionalInformation customAdditionalInformation;
//	
//	@Bean
//	AuthorizationServerTokenServices tokenServices() {
//	    DefaultTokenServices services = new DefaultTokenServices();
//	    services.setClientDetailsService(clientDetailsService);
//	    services.setSupportRefreshToken(true);
//	    services.setTokenStore(tokenStore);
//	    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//	    tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter, customAdditionalInformation));
//	    services.setTokenEnhancer(tokenEnhancerChain);
//	    return services;
//	}
//
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
//	}
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory()
//			.withClient("javaboy")
//				.secret(new BCryptPasswordEncoder().encode("123"))
//				.resourceIds("res1")
//				.authorizedGrantTypes("authorization_code","password", "refresh_token").scopes("all")
//				.redirectUris("http://127.0.0.1:8080/");
//	}
//	
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.authorizationCodeServices(authorizationCodeServices()).authenticationManager(authenticationManager).tokenServices(tokenServices());
//	}
//
//	@Bean
//	AuthorizationCodeServices authorizationCodeServices() {
//		return new InMemoryAuthorizationCodeServices();
//	}
}
