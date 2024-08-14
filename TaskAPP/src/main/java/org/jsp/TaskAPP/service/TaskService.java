package org.jsp.TaskAPP.service;

import java.util.List;

import org.jsp.TaskAPP.payload.TaskDto;

public interface TaskService {

	
	public  TaskDto saveTask(Long id,TaskDto taskDto);
	
	

	public List<TaskDto> getTaskbyuserid(Long id);
	
	public TaskDto getTask(Long userid,Long taskid);
	public void deleteTask(Long userid,Long taskid);
	
}
