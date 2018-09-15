package com.jacob.jdbcService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.BlogDAO;
import com.jacob.dao.UpvoteDAO;
import com.jacob.model.Blog;
import com.jacob.model.Upvote;

@Service("blogService")
public class BlogService implements BlogServiceInterface {

	@Autowired
	private BlogDAO blogDao;
	
	@Autowired
	private UpvoteDAO upvoteDAO;
	
	@Override
	public void saveBlog(Blog blog) {
		blogDao.saveBlog(blog);
	}
	
	public void updateBlog(Blog blog) {
		blogDao.updateBlog(blog);
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
	public void deleteBlog(int id) {
		List<Upvote> ourBlogsVotes = upvoteDAO.getAllUpvotesForABlog(id);
		
		for(int i = 0; i < ourBlogsVotes.size(); i++) {
			int tempVoteId = ourBlogsVotes.get(i).getId();
			System.out.println(tempVoteId);
			upvoteDAO.deleteUpvote(tempVoteId);
		}
		
		blogDao.deleteBlog(id);	
	}
}
