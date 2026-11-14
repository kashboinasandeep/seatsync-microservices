	
	package com.example.demo.service;
	
	import org.springframework.security.authentication.AuthenticationManager;
	import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
	import org.springframework.stereotype.Service;
	
	import com.example.demo.dto.AuthResponse;
	import com.example.demo.dto.LoginRequest;
	import com.example.demo.dto.RefreshTokenRequest;
	
	@Service
	public class AuthService {
	
	    private final AuthenticationManager authenticationManager;
	    private final JwtService jwtService;
	
	    public AuthService(
	            AuthenticationManager authenticationManager,
	            JwtService jwtService) {
	
	        this.authenticationManager = authenticationManager;
	        this.jwtService = jwtService;
	    }
	
	    public AuthResponse login(LoginRequest request) {
	
	        // 1. Authenticate user credentials
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                request.getEmail(),
	                request.getPassword()
	            )
	        );
	
	        // 2. Generate access token
	        String accessToken = jwtService.generateToken(
	            request.getEmail(),
	            "USER"
	        );
	
	        // 3. Generate refresh token
	        String refreshToken = jwtService.generateRefreshToken(
	            request.getEmail()
	        );
	
	        // 4. Return both tokens
	        return new AuthResponse(accessToken, refreshToken);
	    }
	    
	    public AuthResponse refreshToken(RefreshTokenRequest request) {
	
	        // 1. Validate refresh token and extract email
	        String email = jwtService.validateRefreshToken(request.getRefreshToken()   );
	     
	
	        // 2. Generate new access token
	        String accessToken = jwtService.generateToken(email, "USER");
	
	        // 3. Return new access token and existing refresh token
	        return new AuthResponse(
	                accessToken,
	                request.getRefreshToken()
	        );
	    }
	}