package com.expensetrackerapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensetrackerapi.entity.User;
import com.expensetrackerapi.entity.UserModel;
import com.expensetrackerapi.exception.ItemAlreadyExistsException;
import com.expensetrackerapi.exception.ResourceNotFoundException;
import com.expensetrackerapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(UserModel user) throws ItemAlreadyExistsException {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistsException("User is already register with email:"+user.getEmail());
		}
		
		User newUser = new User();
		BeanUtils.copyProperties(user, newUser);
		
		return userRepository.save(newUser);
	}

	@Override
	public User readUser(Long id) {
		return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found for the id"+id));
	}

	@Override
	public User updateUser(UserModel user, Long id) {
		User existingUser = readUser(id);
		
		existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
		existingUser.setEmail(user.getEmail() != null ? user.getName() : existingUser.getEmail());
		existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
 		existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(Long id) {
		User user = readUser(id);
		userRepository.delete(user);
	}

}
