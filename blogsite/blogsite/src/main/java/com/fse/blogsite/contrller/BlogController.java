package com.fse.blogsite.contrller;

import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fse.blogsite.model.Blog;
import com.fse.blogsite.model.MyResponse;
import com.fse.blogsite.repository.BlogRepository;
import com.fse.blogsite.service.BlogService;

@RestController
@RequestMapping("/api/v1.0/blogsite/user/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;
	
    @Autowired
    private BlogRepository blogRepository;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<MyResponse> addBlog(@RequestBody Blog blog , @RequestParam String name) {
    	blog.setName(name);
    	blog.setId(UUID.randomUUID().toString());
    	int result = hasExactly1000Words(blog.getArticle());
    	if (blog.getName().isEmpty() || blog.getName().length() > 20) {
    		MyResponse response = new MyResponse("Name must be 20 characters or fewer.", 400);
    		return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
    	else if (blog.getCategory().isEmpty() || blog.getCategory().length() > 20) {
    		MyResponse response = new MyResponse("Category must be 20 characters or fewer.", 400);
    		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    	else if(result > 1000) {
    		MyResponse response = new MyResponse("The content does not contain 1000 words.", 400);
    		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	}
    	else {
          
    		Blog createdBlog = blogService.addBlog(blog);
	    	//String blogMessage = String.format("New blog by %s: %s", createdBlog.getName(), createdBlog.getArticle());
	    	//blogService.sendBlogMessage(blogMessage);
    		MyResponse response = new MyResponse("Blog is Addded", 200);
            return new ResponseEntity<>( response , HttpStatus.CREATED);
    	}
        
    }
    
    public static int hasExactly1000Words(String content) {
        if (content == null || content.trim().isEmpty()) {
            return 0; 
        }
        String[] words = content.trim().split("\\s+");
        return words.length;
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/info")
    public List<Blog> getItemsByCategory(@RequestParam String category) {
    	//blogService.sendBlogMessage(category);
        return blogService.getItemsByCategory(category);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getall")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete")
    public ResponseEntity<MyResponse> deleteItemsByCategory(@RequestParam String name){
  	  if (!blogRepository.existsByName(name)) {
  		MyResponse response = new MyResponse("Blog not found", 404);
  		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      }
  	  else {
    	  blogService.deleteByBlogname(name);
          //String deleteMessage = String.format("Blog post deleted: %s", name);
         // blogService.sendBlogMessage(deleteMessage);
    	  MyResponse response = new MyResponse("Blog post deleted successfully", 204);
          return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
      }
    	
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get")
    public List<Blog> getItemsByTimeRange(
    		@RequestParam String category,
    		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate) {
        try {
            System.out.println(startDate);
            return blogService.getItemsByTimeRange(category,startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use ISO-8601 format, e.g., 2024-08-01T00:00:00Z.");
        }
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<MyResponse> updateBlog(@PathVariable String id, @RequestBody Blog updatedBlog) throws Exception {
        try {
            Blog updated = blogService.updateBlog(id, updatedBlog);
            String blogMessage = String.format("Updated blog by %s: %s", updated.getName(), updated.getArticle());
	    	//blogService.sendBlogMessage(blogMessage);
	    	 MyResponse response = new MyResponse(blogMessage, 201);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AttributeNotFoundException ex) {
        	 MyResponse response = new MyResponse("Blog not found", 404);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
