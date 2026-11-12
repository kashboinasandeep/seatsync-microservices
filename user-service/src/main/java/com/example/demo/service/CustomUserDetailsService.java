package com.example.demo.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//This is an important part of Spring Security authentication. 
//It tells Spring Security how to find a registered user in our MySQL database.
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(()->
		new UsernameNotFoundException("user not found"));
		return  org.springframework.security.core.userdetails.User 
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles("USER")
				.build();
	}	
}
