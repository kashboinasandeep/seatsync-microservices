package com.example.demo.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	 // Find movie using title
    Optional<Movie> findByTitle(String title);

    // Search movies by title
    List<Movie> findByTitleContainingIgnoreCase(String title);

    // Find movies by genre
    List<Movie> findByGenreIgnoreCase(String genre);

    // Find movies by language
    List<Movie> findByLanguageIgnoreCase(String language);

    // Find movies by genre and language
    List<Movie> findByGenreIgnoreCaseAndLanguageIgnoreCase(
            String genre, String language);

    // Check whether a movie exists by title
    boolean existsByTitleIgnoreCase(String title);
}
