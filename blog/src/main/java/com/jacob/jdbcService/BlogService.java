//@Author: Jacob Duchen
//pretty straight forward stuff here 
//i hope to put more of the messy logic in our controllers in here 

package com.jacob.jdbcService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.BlogDAO;
import com.jacob.model.Blog;
import com.jacob.model.Comment;
import com.jacob.model.Upvote;
import com.jacob.model.WordInBlog;

@Service("blogService")
public class BlogService implements BlogServiceInterface {

	@Autowired
	private BlogDAO blogDao;

	@Autowired
	private UpvoteService upvoteService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private WordInBlogService wordInBlogService;
	
	@Override
	public void saveBlog(Blog blog) {
		blogDao.saveBlog(blog);
	}
	
	public void updateBlog(Blog blog) {
		blogDao.updateBlog(blog);
	}
	//note get all blogs is sorted in blogDao sql statement
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
		List<WordInBlog> ourWordInBlogs = wordInBlogService.getAllWordsInAllBlogsByBlogId(id);
	
		for(int i = 0; i < ourBlogComments.size(); i++) {
			int tempCommentId = ourBlogComments.get(i).getId();
			commentService.deleteComment(tempCommentId);
		}
		
		for(int i = 0; i < ourBlogsVotes.size(); i++) {
			int tempVoteId = ourBlogsVotes.get(i).getId();
			upvoteService.deleteUpvote(tempVoteId);
		}
		
		for(int i = 0; i < ourWordInBlogs.size(); i++) {
			int tempWordInBlogId = ourWordInBlogs.get(i).getId();
			wordInBlogService.deleteWordInBlogFromDb(tempWordInBlogId);
		}
		
		blogDao.deleteBlog(id);	
	}
}
