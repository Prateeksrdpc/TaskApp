package org.jsp.TaskAPP.service;

import org.jsp.TaskAPP.payload.UserDto;

public interface UserService   {
	
	
	public UserDto creatUser(UserDto  userDto);
	public UserDto creatAdmin(UserDto userDto);

}
