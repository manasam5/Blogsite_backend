package com.java.userservice.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.userservice.model.User;
import com.java.userservice.service.UserService;

@RestController
@RequestMapping("/api/v1.0/blogsite/user")
public class UserController {

	   @Autowired
	    private UserService userService;

	    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{8,}$";
	    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
	    
	    public static boolean isValidPassword(String password) {
	        return PASSWORD_PATTERN.matcher(password).matches();
	    }
	    
	    public static boolean isValidEmail(String email) {
	        return email.contains("@") && email.contains(".com");
	    }
	    
	    @CrossOrigin(origins = "http://localhost:4200")
	    @PostMapping("/register")
	    public ResponseEntity<String> registerUser(@RequestBody User user) {
	    	   HttpHeaders headers = new HttpHeaders();
	           headers.setContentType(MediaType.APPLICATION_JSON); // Set content type to JSON

	    	  if (user == null || user.getName().isEmpty() || user.getPassword().isEmpty() || user.getEmailId().isEmpty()) {
	    		  return new ResponseEntity<>("Input data cannot be empty",headers, HttpStatus.BAD_REQUEST);
	          }
	    	  else if(!isValidEmail(user.getEmailId())) {
	    		  return new ResponseEntity<>("Not a valid Email id",headers, HttpStatus.BAD_REQUEST);
	    	  }
	    	  else if(!isValidPassword(user.getPassword())) {
	    		  return new ResponseEntity<>("Password should be alphnumeric and minimum 8 characters",headers, HttpStatus.BAD_REQUEST);
	    	  }
	    	  else {
	    		  userService.registerUser(user);
	    		  return new ResponseEntity<>("User is registered",headers, HttpStatus.CREATED);
	    	  }
	        
	    }
	  
}
