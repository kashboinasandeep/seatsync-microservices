package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleMovieNotFound
							(MovieNotFoundException exception){
		Map<String,Object> errorResponse = Map.of(
				"timestamp",LocalDateTime.now(),
				"status",404,
				"error","NotFound",
				"message",exception.getMessage()
				);
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(errorResponse);
	}
}
