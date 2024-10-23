package com.java.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.java.userservice.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByName(String name);
}
