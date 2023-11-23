package com.expensetrackerapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.expensetrackerapi.security.CustomUserDetailsService;
import com.expensetrackerapi.security.JwtRequestFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public JwtRequestFilter authenticationJwtTokenFilter() {
		return new JwtRequestFilter();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login","/register","/h2-console/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.httpBasic();
	    http.headers().frameOptions().disable();

	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		/*
		 * auth.inMemoryAuthentication()
		 * .withUser("ruchi").password("1234").authorities("admin") .and()
		 * .withUser("test").password("1234").authorities("user") .and()
		 * .passwordEncoder(NoOpPasswordEncoder.getInstance());
		 */
		
		/*
		 * InMemoryUserDetailsManager userDetailsManager = new
		 * InMemoryUserDetailsManager();
		 * 
		 * UserDetails user1 =
		 * User.withUsername("ruchi").password("12345").authorities("admin").build();
		 * UserDetails user2 =
		 * User.withUsername("test").password("12345").authorities("user").build();
		 * 
		 * userDetailsManager.createUser(user1); userDetailsManager.createUser(user2);
		 * 
		 * auth.userDetailsService(userDetailsManager);
		 */
		
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//return NoOpPasswordEncoder.getInstance();
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
