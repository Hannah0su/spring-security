package com.example.ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	/**
	 * 	WebSecurityConfigurerAdapter Deprecated In Spring Security 5.7.0-M2
	 * 	https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
	 * 	SecurityFilterChain 등 필요한 설정을 Bean으로 등록해주어야함
	 */



	@Bean
	public UserDetailsService userDetailsService() {
		var userDetailsService = new InMemoryUserDetailsManager();

		var user = User.withUsername("john")
			.password("12345")
			.authorities("read")
			.build();

		userDetailsService.createUser(user);

		return userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	// @Bean
	// protected void configure(HttpSecurity http) throws Exception {
	// 	http.httpBasic();
	// 	http.authorizeRequests().anyRequest().authenticated();
	// }

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
