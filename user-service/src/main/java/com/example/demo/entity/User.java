package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column(nullable=false)
private String name;

@Column(nullable = false , unique = true)
private String email;

@Column(nullable = false)
private String password;


private LocalDateTime createdAt;


public User() {
	
}

@PrePersist
public void onCreate() {
	createdAt = LocalDateTime.now();
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
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

public LocalDateTime getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}

public User(long id, String name, String email, String password, LocalDateTime createdAt) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.createdAt = createdAt;
}

@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", email=" + email + ", createdAt="
			+ createdAt + "]";
}





}
