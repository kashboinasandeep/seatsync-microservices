package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.MovieRequest;
import com.example.demo.dto.MovieResponse;
import com.example.demo.entity.Movie;
import com.example.demo.exception.MovieNotFoundException;
import com.example.demo.repository.MovieRepository;

@Service
public class MovieService {

private final MovieRepository movieRepository;

// Constructor Injection
public MovieService(MovieRepository movieRepository) {
	this.movieRepository =movieRepository;
}

	//create Movie
	public MovieResponse createMovie(MovieRequest request) {
	Movie movie = new Movie();
	
	movie.setTitle(request.getTitle());
	movie.setDescription(request.getDescription());
	movie.setGenre(request.getGenre());
	movie.setLanguage(request.getLanguage());
	movie.setDuration(request.getDuration());
	movie.setReleaseDate(request.getReleaseDate());
	movie.setRating(request.getRating());
	movie.setPosterUrl(request.getPosterUrl());
	
	Movie savedMovie = movieRepository.save(movie);
	return convertToResponse(savedMovie);	
}

	//Get All Movies
	public List<MovieResponse> getAllMovies(){
		return movieRepository.findAll()
				.stream()
				.map(this::convertToResponse)
				.toList();
	}
	
	//Get Movie By Id
	public MovieResponse getMovie(Long id) {
		   Movie movie = movieRepository.findById(id)
		   .orElseThrow(()->
		   new MovieNotFoundException("Movie not found with ID: " + id));
		   return convertToResponse(movie);		   
		   
	} 
	
	//Update Movie
	public MovieResponse updateMovie(Long id,MovieRequest request) {
		Movie movie = movieRepository.findById(id)
				.orElseThrow(()-> new MovieNotFoundException("Movie Not Found with Id: "+ id));
				  	movie.setTitle(request.getTitle());
			        movie.setDescription(request.getDescription());
			        movie.setGenre(request.getGenre());
			        movie.setLanguage(request.getLanguage());
			        movie.setDuration(request.getDuration());
			        movie.setReleaseDate(request.getReleaseDate());
			        movie.setRating(request.getRating());
			        movie.setPosterUrl(request.getPosterUrl());
			 Movie updatedMovie = movieRepository.save(movie);
			 return convertToResponse(updatedMovie);
	}
	
	//Delete By Id
	public void deleteMovie(Long id) {
		
		if(!movieRepository.existsById(id)) {
			throw new MovieNotFoundException("Movie Not Found With Id: "+ id);
		}
		movieRepository.deleteById(id);
		
	}
	
	
	 // Convert Entity to Response DTO
	
	private MovieResponse convertToResponse (Movie movie) {
		return new MovieResponse(
				movie.getId(),
				movie.getTitle(),
				movie.getDescription(),
				movie.getGenre(),
				movie.getLanguage(),
				movie.getDuration(),
				movie.getReleaseDate(),
				movie.getRating(),
				movie.getPosterUrl(),
				movie.getCreatedAt()	);
	}
	
}
