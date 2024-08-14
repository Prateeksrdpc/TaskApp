package org.jsp.TaskAPP.securityconfg;

import org.jsp.TaskAPP.security.JWTAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JWTAuthFilter jwtAuthfilter;
	
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		
		http
	   .csrf().disable()
	  .authorizeRequests().requestMatchers("/api/admin/**").hasAuthority("ADMIN")
	  .requestMatchers("/api/user/**").hasAuthority("USER")
	  .requestMatchers("/api/auth/**")
	  .permitAll()
		.anyRequest()
	    .authenticated();
		http.addFilterBefore(jwtAuthfilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration  authconfig) throws Exception
	{
		return authconfig.getAuthenticationManager();
	}
	
	

}
