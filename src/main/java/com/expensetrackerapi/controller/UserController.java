package com.expensetrackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetrackerapi.entity.User;
import com.expensetrackerapi.entity.UserModel;
import com.expensetrackerapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@Operation(summary = "Get logged in user profile", description = "Returns logged in user profile")
	@GetMapping("/profile")
	public ResponseEntity<User> readUser(){
		return new ResponseEntity<User>(userService.readUser(),HttpStatus.OK);
	}
	
	@Operation(summary = "Update user", description = "Updates user")
	@PutMapping("/profile")
	public ResponseEntity<User> updateUser(@RequestBody UserModel user){
		return new ResponseEntity<User>(userService.updateUser(user),HttpStatus.OK);
	}
	@Operation(summary = "Delete logged in user", description = "Deletes logged in user")
	@DeleteMapping("/deactivate")
	public ResponseEntity<HttpStatus> deleteUser(){
		userService.deleteUser();
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	

}
