package com.xhld.authenticationservice.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import com.xhld.authenticationservice.secruity.JwtAuthenticationEntryPoint;
import com.xhld.authenticationservice.secruity.JwtAuthenticationProvider;
import com.xhld.authenticationservice.secruity.JwtAwareAuthenticationFailureHandler;
import com.xhld.authenticationservice.secruity.JwtTokenAuthenticationProcessingFilter;
import com.xhld.authenticationservice.secruity.SkipPathRequestMatcher;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired 
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    
    @Autowired private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtAwareAuthenticationFailureHandler  jwtAwareAuthenticationFailureHandler;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//        return new JwtAuthenticationTokenFilter();
//    }
    @Bean
    public JwtTokenAuthenticationProcessingFilter authenticationTokenFilterBean() throws Exception {
//    	List<String> pathsToSkip = Arrays.asList("/pages/index.html", "/auth/register");
    	List<String> pathsToSkip = Arrays.asList("/pages/index.html");
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, "/pages/**");
//        JwtTokenAuthenticationProcessingFilter filter =  new JwtTokenAuthenticationProcessingFilter(jwtAwareAuthenticationFailureHandler,matcher);
        JwtTokenAuthenticationProcessingFilter filter =  new JwtTokenAuthenticationProcessingFilter(jwtAwareAuthenticationFailureHandler,AnyRequestMatcher.INSTANCE);
        
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }
    
    

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.png",
                        "/**/*.ico",
                        "/**/*.ttf",
                        "/**/*.woff"
                ).permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/auth/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 添加JWT filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}
