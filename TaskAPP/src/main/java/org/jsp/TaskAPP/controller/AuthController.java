package org.jsp.TaskAPP.controller;

import org.jsp.TaskAPP.entity.Users;
import org.jsp.TaskAPP.payload.JwtAuthResponse;
import org.jsp.TaskAPP.payload.LoginDto;
import org.jsp.TaskAPP.payload.UserDto;
import org.jsp.TaskAPP.security.JWTTokenProvider;
import org.jsp.TaskAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UserService userservice;
	
	@Autowired
	private AuthenticationManager authmanager;
	@Autowired
	private JWTTokenProvider jwt;
	//Postmapping  store user in Database

	@PostMapping("/user/register")
	public ResponseEntity<UserDto> createUser( @RequestBody UserDto userDto)
	{
	
		return new ResponseEntity<>( userservice.creatUser(userDto),HttpStatus.CREATED);
	}
	@PostMapping("/admin/register")
	public ResponseEntity<UserDto> createAdmin( @RequestBody UserDto userDto)
	{
		return new ResponseEntity<>( userservice.creatAdmin(userDto),HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public  ResponseEntity<JwtAuthResponse>  loginUser(@RequestBody LoginDto logindto)
	{
		Authentication auth=authmanager.authenticate(new UsernamePasswordAuthenticationToken(logindto.getEmail(), logindto.getPassword()));
		System.out.println(auth);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String token=jwt.generateToken(auth);
		return  ResponseEntity.ok(new JwtAuthResponse(token));
	}

}
