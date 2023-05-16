package com.example.ex5.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.ex5.security.CustomAuthenticationProvider;

@Configuration
public class SecurityConfig {

	/**
	 * 	WebSecurityConfigurerAdapter Deprecated In Spring Security 5.7.0-M2
	 * 	https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
	 */

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) {
	// 	auth.authenticationProvider(authenticationProvider);
	// }

	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(customAuthenticationProvider);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic()
			.and()
			.build();
	}
}
