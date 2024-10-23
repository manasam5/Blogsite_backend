package com.fse.blogsite.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.blogsite.model.Blog;
import com.fse.blogsite.repository.BlogRepository;

@Service
public class BlogService {
	 @Autowired
	    private BlogRepository blogRepository;
	   
	//    @Autowired
	 //   private KafkaTemplate<String, String> kafkaTemplate;

	    //private static final String TOPIC = "blogs";s

	    public Blog addBlog(Blog blog) {
	    	blog.setTimestamp(new Date());
	    	System.out.println(blog.getTimestamp());
	        return blogRepository.save(blog);
	    }
	   
	    public List<Blog> getItemsByCategory(String category) {
	        return blogRepository.findByCategory(category);
	    }
	    
	    public List<Blog> getAllBlogs() {
	        return blogRepository.findAll();
	    }
	    
	    public void deleteByBlogname(String name) {
	    	blogRepository.deleteByName(name);
	    }
	    
	    public List<Blog> getItemsByTimeRange(String category,Date startTime, Date endTime) {
	        return blogRepository.findByCategoryAndTimeStamp(category,startTime, endTime);
	    }

	//	public void sendBlogMessage(String blogMessage) {
	//		kafkaTemplate.send(TOPIC, blogMessage);
	//	}
		
	    public Blog updateBlog(String id, Blog updatedBlog) throws Exception {
	        if (!blogRepository.existsById(id)) {
	            throw new Exception("Blog not found with id: " + id);
	        }
	        updatedBlog.setId(id);
	        updatedBlog.setTimestamp(new Date());
	        return blogRepository.save(updatedBlog);
	    }

}
