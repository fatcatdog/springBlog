//@Author: Jacob Duchen


package com.jacob.jdbcService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.BlogDAO;
import com.jacob.model.Blog;
import com.jacob.model.Comment;
import com.jacob.model.Upvote;

@Service("blogService")
public class BlogService implements BlogServiceInterface {

	@Autowired
	private BlogDAO blogDao;

	@Autowired
	private UpvoteService upvoteService;
	
	@Autowired
	private CommentService commentService;
	
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
		List<Upvote> ourBlogsVotes = upvoteService.getAllUpvotesForABlog(id);
		List<Comment> ourBlogComments = commentService.getAllCommentsForABlog(id);
		
		for(int i = 0; i < ourBlogComments.size(); i++) {
			int tempCommentId = ourBlogComments.get(i).getId();
			commentService.deleteComment(tempCommentId);
		}
		
		for(int i = 0; i < ourBlogsVotes.size(); i++) {
			int tempVoteId = ourBlogsVotes.get(i).getId();
			upvoteService.deleteUpvote(tempVoteId);
		}
		
		blogDao.deleteBlog(id);	
	}
}
