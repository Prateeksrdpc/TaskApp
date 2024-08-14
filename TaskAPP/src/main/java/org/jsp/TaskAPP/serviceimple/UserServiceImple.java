 package org.jsp.TaskAPP.serviceimple;

import org.apache.catalina.User;
import org.jsp.TaskAPP.entity.Role;
import org.jsp.TaskAPP.entity.Users;
import org.jsp.TaskAPP.payload.UserDto;
import org.jsp.TaskAPP.repository.UserRepositry;
import org.jsp.TaskAPP.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserRepositry userRepositry;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper modelmapper;

	
	@Override
	public UserDto creatUser(UserDto userDto) {
		// TODO Auto-generated method stub
		Users users=modelmapper.map(userDto, Users.class);
		users.setRole(Role.USER);
		users.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		Users savedUsers=userRepositry.save(users);
		
		return modelmapper.map(savedUsers, UserDto.class);
		
	}


	@Override
	public UserDto creatAdmin(UserDto userDto) {
		Users users=modelmapper.map(userDto, Users.class);
		users.setRole(Role.ADMIN);
		users.setPassword(passwordEncoder.encode(userDto.getPassword()));

		Users savedUsers=userRepositry.save(users);
		
		return modelmapper.map(savedUsers, UserDto.class);
	}
}
