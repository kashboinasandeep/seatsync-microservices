package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder=passwordEncoder;
	}
	
	public UserResponse registerUser(RegisterRequest request) {
		
		 // 1. Check whether email already exists
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new IllegalArgumentException("Email already registered");
		}
		
		//create user
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		
        // 3. Encode password
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		//Save user in database
		User savedUser =  userRepository.save(user);
		
		// 5. Convert entity into response DTO
		return new UserResponse(
				savedUser.getId(),
				savedUser.getName(),
				savedUser.getEmail()
		);
	}
	
}
