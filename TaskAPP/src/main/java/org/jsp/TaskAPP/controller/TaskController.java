package org.jsp.TaskAPP.controller;

import java.util.List;

import org.jsp.TaskAPP.payload.TaskDto;
import org.jsp.TaskAPP.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    // Save the task - only accessible by ADMIN role
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/{userid}/tasks")
    public ResponseEntity<TaskDto> saveTask(@PathVariable(name="userid") Long id, 
                                            @RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.saveTask(id, taskDto), HttpStatus.CREATED);
    }

    // Get all tasks for a user - only accessible by USER role
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/{userid}/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByUserId(@PathVariable(name="userid") Long userid) {
        return new ResponseEntity<>(taskService.getTaskbyuserid(userid), HttpStatus.OK);
    }

    // Get individual task - only accessible by USER role
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/{userid}/tasks/{taskid}")
    public ResponseEntity<TaskDto> getTask(@PathVariable(name = "userid") Long userid,
                                           @PathVariable(name = "taskid") Long taskid) {
        return new ResponseEntity<>(taskService.getTask(userid, taskid), HttpStatus.OK);
    }

    // Delete individual task - only accessible by ADMIN role
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/admin/{userid}/tasks/{taskid}")
    public ResponseEntity<String> deleteTask(@PathVariable(name = "userid") Long userid,
                                             @PathVariable(name = "taskid") Long taskid) {
        taskService.deleteTask(userid, taskid);
        return new ResponseEntity<>(" Admin Task deleted successfully", HttpStatus.OK);
    }
}
