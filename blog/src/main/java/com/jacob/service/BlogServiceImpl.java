package com.jacob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.model.Blog;
import com.jacob.repository.BlogRepository;

//@Service("blogService")
public class BlogServiceImpl implements BlogService {
	 
//	 @Autowired
//	 private BlogRepository blogRepository;
//
//	 @Override
//	public void saveBlog(Blog blog) {
//		  blogRepository.save(blog);
//	}
//	 
//	 @Override
//	public List<Blog> getAllBlogs(){
//		List<Blog> ourBlogs = blogRepository.findAll(); 
//		return ourBlogs; 
//	}
//	
//	 @Override
//	 public Blog getBlog(int id) {
//		Blog tempBlog = blogRepository.findById(id);
//		
//		return tempBlog; 
//	}
//	 
//	 @Override
//	 public void deleteBlog(long id) {
//		 blogRepository.deleteById(id);
//	 }
}
