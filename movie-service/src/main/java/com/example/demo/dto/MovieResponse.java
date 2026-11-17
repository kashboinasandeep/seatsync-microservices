package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MovieResponse {

    private Long id;
    private String title;
    private String description;
    private String genre;
    private String language;
    private Integer duration;
    private LocalDate releaseDate;
    private Double rating;
    private String posterUrl;
    private LocalDateTime createdAt;

    public MovieResponse() {
    }

    public MovieResponse(Long id, String title, String description,
            String genre, String language, Integer duration,
            LocalDate releaseDate, Double rating, String posterUrl,
            LocalDateTime createdAt) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.posterUrl = posterUrl;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getDuration() {
        return duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
