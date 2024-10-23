package com.fse.blogsite.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Blogsite")
public class Blog {
	  @Id
	    private String id;
		private String name;
	    private String category;
	    private String article;
	    private String authorName;
	    //LocalDateTime currentDateTime = LocalDateTime.now();
	    private Date timestamp;
		public Blog(String id, String name, String category, String article, String authorName, Date timestamp) {
			super();
			this.id = id;
			this.name = name;
			this.category = category;
			this.article = article;
			this.authorName = authorName;
			this.timestamp = timestamp;
		}
		
	    public Blog() {
			// TODO Auto-generated constructor stub
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getArticle() {
			return article;
		}
		public void setArticle(String article) {
			this.article = article;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getAuthorName() {
			return authorName;
		}
		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}
		public Date getTimestamp() { 
			 return timestamp; 
		}
		public void setTimestamp(Date timestamp) { 
			this.timestamp = timestamp; 
		}
}
