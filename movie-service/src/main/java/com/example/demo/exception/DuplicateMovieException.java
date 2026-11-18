package com.example.demo.exception;



public class DuplicateMovieException extends RuntimeException {

    public DuplicateMovieException(String message) {
        super(message);
    }
}
