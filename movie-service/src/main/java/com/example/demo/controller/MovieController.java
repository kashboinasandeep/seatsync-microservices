package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MovieRequest;
import com.example.demo.dto.MovieResponse;
import com.example.demo.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
	
private final MovieService movieService;

public MovieController(MovieService movieService) {
	this.movieService = movieService;
}

//Create movie 
@PostMapping
public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest movieRequest){
	MovieResponse response = movieService.createMovie(movieRequest);
	return new ResponseEntity<>(response,HttpStatus.CREATED);
}

//Get All Movies
@GetMapping
public ResponseEntity<List<MovieResponse>> getAllMovies(){
	List<MovieResponse> allMovies = movieService.getAllMovies();
	return  ResponseEntity.ok(allMovies);
}

// Get Movie By ID
@GetMapping("/{id}")
public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id){
	MovieResponse movieById = movieService.getMovie(id);
	return ResponseEntity.ok(movieById);
}

//Update movie
@PutMapping("/{id}")
public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id ,
								@Valid @RequestBody MovieRequest request){
	MovieResponse updateMovie = movieService.updateMovie(id, request);
	return ResponseEntity.ok(updateMovie);
}

//Delete Movie
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteMovie (@PathVariable Long id){
	movieService.deleteMovie(id);
	return ResponseEntity.noContent().build();
}




}
