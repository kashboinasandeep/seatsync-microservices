
package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.TheaterRequest;
import com.example.demo.dto.TheaterResponse;
import com.example.demo.service.TheaterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    // Create Theater
    @PostMapping
    public ResponseEntity<TheaterResponse> createTheater(
            @Valid @RequestBody TheaterRequest request) {

        TheaterResponse response = theaterService.createTheatre(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Theaters
    @GetMapping
    public ResponseEntity<List<TheaterResponse>> getAllTheaters() {

        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    // Get Theater By ID
    @GetMapping("/{id}")
    public ResponseEntity<TheaterResponse> getTheaterById(
            @PathVariable Long id) {

        return ResponseEntity.ok(theaterService.getTheater(id));
    }

    // Update Theater
    @PutMapping("/{id}")
    public ResponseEntity<TheaterResponse> updateTheater(
            @PathVariable Long id,
            @Valid @RequestBody TheaterRequest request) {

        return ResponseEntity.ok(
                theaterService.updateTheater(id, request));
    }

    // Delete Theater
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {

        theaterService.deleteTheater(id);

        return ResponseEntity.noContent().build();
    }
}
