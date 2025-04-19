package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig 
{
	private JwtAuthenticationFilter jwtAuthFilter;
	private CustomAuthenticationEntryPoint customAuth;
	

	public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, CustomAuthenticationEntryPoint customAuth) {
		super();
		this.jwtAuthFilter = jwtAuthFilter;
		this.customAuth = customAuth;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(auth->
		
//		auth.requestMatchers(HttpMethod.POST,"/role/**").hasRole("ADMIN")
		auth.requestMatchers("/role/**").hasRole("ADMIN")
		.requestMatchers("/public/**").permitAll()
		.anyRequest().permitAll())
		.exceptionHandling(exception->exception.authenticationEntryPoint(customAuth))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		;
		
		
		
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception 
	{
		return configuration.getAuthenticationManager();
		
	}

}
