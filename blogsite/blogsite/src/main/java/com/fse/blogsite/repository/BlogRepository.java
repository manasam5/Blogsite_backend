package com.fse.blogsite.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fse.blogsite.model.Blog;


public interface BlogRepository extends MongoRepository<Blog, String>  {
	 @Query("{ 'category': ?0, 'timestamp': { $gte: ?1, $lte: ?2 } }")
	 List<Blog> findByCategoryAndTimeStamp(String category,Date startTime, Date endTime);
	 List<Blog> findByCategory(String category);
	 void deleteByName(String category);
	boolean existsByName(String name);
}
