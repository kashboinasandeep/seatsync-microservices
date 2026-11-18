package com.example.demo.service;

import com.example.demo.TheaterServiceApplication;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TheaterRequest;
import com.example.demo.dto.TheaterResponse;
import com.example.demo.entity.Theater;
import com.example.demo.exception.DuplicateTheaterException;
import com.example.demo.exception.TheaterNotFoundException;
import com.example.demo.repository.TheaterRepository;

@Service
public class TheaterService {
	
private final TheaterServiceApplication theaterServiceApplication;
private final TheaterRepository theaterRepository;

public TheaterService(TheaterRepository theaterRepository, TheaterServiceApplication theaterServiceApplication) {
	this.theaterRepository=theaterRepository;
	this.theaterServiceApplication = theaterServiceApplication;
}

//create Theater
public TheaterResponse createTheatre(TheaterRequest request) {
	
	if(theaterRepository.existsByNameIgnoreCase(request.getName())) {
		throw new DuplicateTheaterException("Theater Already Exists By Name:" +request.getName());
	}
	Theater theater  = new Theater();
	theater.setName(request.getName());
	theater.setAddress(request.getAddress());
	theater.setCity(request.getCity());
	theater.setPincode(request.getPincode());
	theater.setState(request.getState());
	theater.setContactNumber(request.getContactNumber());
	Theater savedTheater = theaterRepository.save(theater);
	return convertToResponse(savedTheater);

}

//getAllTheaters
public List<TheaterResponse> getAllTheaters(){
	return theaterRepository.findAll()
			.stream()
			.map(this::convertToResponse)
			.toList();
}

//getTheater
public TheaterResponse getTheater(Long id) {
	Theater theater= theaterRepository.findById(id)
	.orElseThrow(()-> new TheaterNotFoundException("Theater Not Found With this Id: "+ id));
	return convertToResponse(theater);
}

//updateTheater
public TheaterResponse updateTheater(Long id , TheaterRequest request) {
	Theater theater = theaterRepository.findById(id)
		.orElseThrow(()-> new RuntimeException("Theater Not Found With this Id: "+ id));
	theater.setName(request.getName());
	theater.setAddress(request.getAddress());
	theater.setCity(request.getCity());
	theater.setPincode(request.getPincode());
	theater.setState(request.getState());
	theater.setContactNumber(request.getContactNumber());
	Theater updatedTheater = theaterRepository.save(theater);
	return convertToResponse(updatedTheater);
}

//delete Theater
public void deleteTheater(Long id) {
	if(!theaterRepository.existsById(id)) {
		throw new RuntimeException("Movie Not Found With Id: "+ id);
	}
	theaterRepository.deleteById(id);
}

// Convert Entity to Response DTO
private TheaterResponse convertToResponse(Theater theater) {

    return new TheaterResponse(
        theater.getId(),
        theater.getName(),
        theater.getAddress(),
        theater.getCity(),
        theater.getState(),
        theater.getPincode(),
        theater.getContactNumber(),
        theater.getCreatedAt()
    );
}


}
