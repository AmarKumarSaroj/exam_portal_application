package com.onlineExamination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineExamination.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	

}
