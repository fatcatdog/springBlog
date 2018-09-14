package com.jacob.jdbcService;

import java.util.List;

import com.jacob.model.Blog;

public interface BlogServiceInterface {
	
	public void saveBlog(Blog blog);
	
	public List<Blog> getAllBlogs();
	
//	public List<Blog> getAllBlogsSortedByUpvotes();
	
	public Blog getBlog(int id);
	
	public void deleteBlog(long id);
}
