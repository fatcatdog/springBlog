package com.jacob.jdbcService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.BlogDAO;
import com.jacob.model.Blog;
import com.jacob.model.Upvote;

@Service("blogService")
public class BlogService implements BlogServiceInterface {

	@Autowired
	private BlogDAO blogDao;
	
	@Autowired
	private UpvoteService upvoteService;
	
	@Override
	public void saveBlog(Blog blog) {
		blogDao.saveBlog(blog);
	}

	@Override
	public List<Blog> getAllBlogs() {
		return blogDao.getAllBlogs();
	}

	@Override
	public Blog getBlog(int id) {
		
		return blogDao.getBlog(id);
	}

	@Override
	public void deleteBlog(long id) {
		blogDao.deleteBlog(id);	
	}
	
//	@Override 
//	public List<Blog> getAllBlogsSortedByUpvotes(){
//		 List<Blog> allBlogs = blogDao.getAllBlogs();
//		 List<Upvote> allUpvotes = upvoteService.getAllUpvotes(); 
//		 
//		 
//	}


}
