/**
 * 
 */
package com.lzh.authenticationservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.alibaba.druid.sql.dialect.oracle.ast.clause.ModelClause.MainModelClause;
import com.lzh.authenticationservice.service.impl.UserServiceImpl;

/**
 * @author 51937
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    @Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
    
    @Autowired
    UserServiceImpl userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("sang")
//                .password(new BCryptPasswordEncoder().encode("123"))
//                .roles("admin")
//                .and()
//                .withUser("javaboy")
//                .password(new BCryptPasswordEncoder().encode("123"))
//                .roles("user");
    	auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().formLogin();
    }
    
    public static void main(String [] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123").length());
		
	}
}
