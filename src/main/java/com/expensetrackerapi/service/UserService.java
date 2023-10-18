package com.expensetrackerapi.service;

import com.expensetrackerapi.entity.User;
import com.expensetrackerapi.entity.UserModel;
import com.expensetrackerapi.exception.ItemAlreadyExistsException;

public interface UserService {
	
	User createUser(UserModel user) throws ItemAlreadyExistsException;
	
	User readUser(Long id);
	
	User updateUser(UserModel user,Long id);
	
	void deleteUser(Long id);

}
