package org.jsp.TaskAPP.repository;

import java.util.Optional;

import org.jsp.TaskAPP.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositry  extends JpaRepository<Users, Long> {

	Optional<Users> findByEmail(String email);

}
