package com.expensetrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetrackerapi.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

	Boolean existsByEmail(String email);
	
}
