package com.java.userservice.model;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "Users")
public class User {
	 @Id
	    private String id;
	 	private String Name;
	    private String Password;
	    private String EmailId;
	    
	    // Constructors, Getters, and Setters
	    public User() {}
	    
	    public User(String Name, String Password, String EmailId) {
	        this.Name = Name;
	        this.Password = Password;
	        this.EmailId = EmailId;
	    }
	    
	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }
		public String getName() {
			return Name;
		}
		public void setName(String Name) {
			this.Name = Name;
		}
		public String getPassword() {
			return Password;
		}
		public void setPassword(String Password) {
			this.Password = Password;
		}
		public String getEmailId() {
			return EmailId;
		}
		public void setEmailId(String EmailId) {
			this.EmailId = EmailId;
		}
	   
}
