//@Author: Jacob Duchen


package com.jacob.jdbcService;

import java.util.List;

import com.jacob.model.Blog;
import com.jacob.model.Comment;
import com.jacob.model.User;

public interface CommentServiceInterface {

	public Comment getComment(int id);
	public void deleteComment(int id);
	public void updateComment(Comment comment);
    public void saveComment(Comment comment);
	public List<Comment> getAllCommentsForABlog(int id);
	public List<Integer> getAllCommentsCount(List<Blog> blogs);
	public int getANewId();	
	public int getCommentCountForABlog(int blogId);	
	public User getAuthorFromComment(int id); 
	public List<String> getAuthorsOfCommentsForABlog(int id);
	public List<String> getContentOfCommentsForABlog(int id);

}
