package com.jacob.jdbcService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.BlogDAO;
import com.jacob.model.Blog;

@Service("blogService")
public class BlogService implements BlogServiceInterface {

	@Autowired
	private BlogDAO blogDao;
	
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

}
