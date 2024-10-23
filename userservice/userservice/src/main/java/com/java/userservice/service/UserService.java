package com.java.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.userservice.model.User;
import com.java.userservice.repository.UserRepository;

@Service
public class UserService {
	  @Autowired
	    private UserRepository userRepository;

	    public User registerUser(User user) {
	        return userRepository.save(user);
	    }
}
