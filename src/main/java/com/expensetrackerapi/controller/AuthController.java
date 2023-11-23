package com.expensetrackerapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetrackerapi.entity.AuthModel;
import com.expensetrackerapi.entity.JWTResponse;
import com.expensetrackerapi.entity.User;
import com.expensetrackerapi.entity.UserModel;
import com.expensetrackerapi.exception.ItemAlreadyExistsException;
import com.expensetrackerapi.security.CustomUserDetailsService;
import com.expensetrackerapi.service.UserService;
import com.expensetrackerapi.util.JwtTokenUtil;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/login")
	public ResponseEntity<JWTResponse> login(@RequestBody AuthModel authModel) throws Exception {

		autheticate(authModel.getEmail(),authModel.getPassword());
		//generate the jwt
		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authModel.getEmail());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return new ResponseEntity<JWTResponse>(new JWTResponse(token) ,HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<User> save(@Valid @RequestBody UserModel user) throws ItemAlreadyExistsException {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	private void autheticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("User disabled");
		}catch (BadCredentialsException e) {
			throw new Exception("Bad credentials");
		}
	}
}
