package org.jsp.TaskAPP.repository;

import java.util.List;

import org.jsp.TaskAPP.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepositry  extends JpaRepository<Task, Long>  {

	public List<Task> findAllByUserId(Long userid);

}
