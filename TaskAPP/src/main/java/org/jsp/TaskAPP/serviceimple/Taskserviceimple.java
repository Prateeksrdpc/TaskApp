package org.jsp.TaskAPP.serviceimple;

import java.util.List;
import java.util.stream.Collectors;

import org.jsp.TaskAPP.entity.Task;
import org.jsp.TaskAPP.entity.Users;
import org.jsp.TaskAPP.exceptions.ApiException;
import org.jsp.TaskAPP.exceptions.TaskNotFound;
import org.jsp.TaskAPP.exceptions.UserNotFound;
import org.jsp.TaskAPP.payload.TaskDto;
import org.jsp.TaskAPP.repository.TaskRepositry;
import org.jsp.TaskAPP.repository.UserRepositry;
import org.jsp.TaskAPP.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Taskserviceimple implements TaskService {
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private UserRepositry userRepositry;
	
	@Autowired
	private TaskRepositry taskRepositry;

	@Override
	public TaskDto saveTask(Long id, TaskDto taskDto) {
		// TODO Auto-generated method stub
		
		Users user=userRepositry.findById(id).orElseThrow(
				() -> new UserNotFound(String.format("User Id %d is not found", id)));
		
		if(user.getRole().name()=="ADMIN") {
			throw new UserNotFound(String.format("User Id %d is not found", id));
		}
		
		Task task=modelmapper.map(taskDto, Task.class);
		//setting the user to Task
		task.setUser(user);
		//then We are Save the Task into DB
		Task saveTask=taskRepositry.save(task);
		return modelmapper.map(saveTask, TaskDto.class);
	}

	@Override
	public List<TaskDto> getTaskbyuserid(Long id) {
		// TODO Auto-generated method stub
		Users user=userRepositry.findById(id).orElseThrow(
				() -> new UserNotFound(String.format("User Id %d is not found", id)));
		List<Task> tasks=taskRepositry.findAllByUserId(id);
		
		return tasks.stream().map(
				task -> modelmapper.map(task, TaskDto.class)
				).collect(Collectors.toList());
		
		
	}

	@Override
	public TaskDto getTask(Long userid, Long taskid) {
		// TODO Auto-generated method 

		Users user=userRepositry.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %d is not found",userid)));
		
		Task task=taskRepositry.findById(taskid).orElseThrow(
				()-> new TaskNotFound(String.format("Task Id %d is not found",taskid)));
		if(user.getId() !=task.getUser().getId())
		{
			throw new ApiException(String.format("Task Id %d is not belong to User Id %d", taskid,userid));
		}
		return modelmapper.map(task, TaskDto.class);
	}

	@Override
	public void deleteTask(Long userid, Long taskid) {
		// TODO Auto-generated method stub
		Users user=userRepositry.findById(userid).orElseThrow(
				() -> new UserNotFound(String.format("User Id %d is not found",userid)));
		
		Task task=taskRepositry.findById(taskid).orElseThrow(
				()-> new TaskNotFound(String.format("Task Id %d is not found",taskid)));
		if(user.getId() !=task.getUser().getId())
		{
			throw new ApiException(String.format("Task Id %d is not belong to User Id %d", taskid,userid));
		}
		taskRepositry.deleteById(taskid);
		
	}
	
	
	

}
