/*
 * package com.openspace.HospitalMgnt.Security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class WebConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired private CustomUserDetailsService customUserDetailsService;
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.httpBasic();
 * http.csrf().disable().authorizeRequests().anyRequest().authenticated(); }
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.userDetailsService(customUserDetailsService); } }
 */