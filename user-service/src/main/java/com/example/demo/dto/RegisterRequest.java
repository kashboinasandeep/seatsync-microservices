package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
	
@NotBlank(message="Name is required")
private String name;

@NotBlank(message = "Email is Required")
@Email(message = "Enter valid Email Address")
private String email;

@NotBlank(message = "password is Required")
@Size(min = 8 , message = "Password must contain atleast 8 characters")
private String password;

public RegisterRequest() {
}

public RegisterRequest(String name, String email, String password) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Override
public String toString() {
	return "RegisterRequest [name=" + name + ", email=" + email + ", password=" + password + "]";
}







}
