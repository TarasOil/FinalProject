package com.oliinyk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.oliinyk.config.jwt.JWTTokenFilterConfigurer;
import com.oliinyk.config.jwt.JWTTokenProvider;
import com.oliinyk.entity.enums.UserRole;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		//TODO
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.POST, "/users/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.PUT, "/users/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.DELETE, "/users/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority());
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/teachers/**").permitAll()
		.antMatchers(HttpMethod.POST, "/teachers/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.PUT, "/teachers/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.DELETE, "/teachers/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority());
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/students/**").hasAnyAuthority(UserRole.ROLE_ADMIN.getAuthority(), UserRole.ROLE_TEACHER.getAuthority())
		.antMatchers(HttpMethod.POST, "/students/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.PUT, "/students/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.DELETE, "/students/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority());
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/singers/**").hasAnyAuthority(UserRole.ROLE_ADMIN.getAuthority(), UserRole.ROLE_TEACHER.getAuthority())
		.antMatchers(HttpMethod.POST, "/singers/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.PUT, "/singers/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.DELETE, "/singers/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority());
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/songs/**").hasAnyAuthority(UserRole.ROLE_ADMIN.getAuthority(), UserRole.ROLE_TEACHER.getAuthority())
		.antMatchers(HttpMethod.POST, "/songs/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.PUT, "/songs/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.DELETE, "/songs/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority());
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/schedule/**").hasAnyAuthority(UserRole.ROLE_ADMIN.getAuthority(), UserRole.ROLE_TEACHER.getAuthority())
		.antMatchers(HttpMethod.POST, "/schedule/**").hasAnyAuthority(UserRole.ROLE_ADMIN.getAuthority(), UserRole.ROLE_TEACHER.getAuthority())
		.antMatchers(HttpMethod.PUT, "/schedule/**").hasAnyAuthority(UserRole.ROLE_ADMIN.getAuthority(), UserRole.ROLE_TEACHER.getAuthority())
		.antMatchers(HttpMethod.DELETE, "/schedule/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority());
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/exams/**").hasAnyAuthority(UserRole.ROLE_ADMIN.getAuthority(), UserRole.ROLE_TEACHER.getAuthority())
		.antMatchers(HttpMethod.POST, "/exams/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.PUT, "/exams/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.DELETE, "/exams/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority());
		
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/competitions/**").hasAnyAuthority(UserRole.ROLE_ADMIN.getAuthority(), UserRole.ROLE_TEACHER.getAuthority())
		.antMatchers(HttpMethod.POST, "/competitions/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.PUT, "/competitions/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority())
		.antMatchers(HttpMethod.DELETE, "/competitions/**").hasAuthority(UserRole.ROLE_ADMIN.getAuthority());
		
		http.apply(new JWTTokenFilterConfigurer(jwtTokenProvider));
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {

	}
}
