package org.fkit.fileuploadtest.repository;

import org.fkit.fileuploadtest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

public interface UserReopsotory extends JpaRepository<User,String>{
	User findByUsername(String username);
    
    User findUserById(String id);
}
