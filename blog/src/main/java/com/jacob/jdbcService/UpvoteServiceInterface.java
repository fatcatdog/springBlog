package com.jacob.jdbcService;

import java.util.List;

import com.jacob.model.Blog;
import com.jacob.model.Upvote;

public interface UpvoteServiceInterface {
	
	public void saveUpvote(Upvote upvote);
	
	public List<Upvote> getAllUpvotes();
	
	public List<Integer> getUpvoteCountsForEachBlogInAList(List<Blog> blogs);
		
	public Upvote getUpvote(int id);
	
	public void deleteUpvote(int id);
	
	public int countUpvotes(int blogId); 
		
	public boolean checkIfUserHasVotedOnThisBlogYet(int userId, int blogId);
	
	public int getUserUpvoteByUserIdAndBlogId(int userId, int blogId);
}
