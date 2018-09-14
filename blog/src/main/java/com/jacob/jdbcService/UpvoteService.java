package com.jacob.jdbcService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.UpvoteDAO;
import com.jacob.model.Upvote;

@Service("upvoteService")
public class UpvoteService  implements UpvoteServiceInterface {

	@Autowired
	private UpvoteDAO upvoteDao;
	
	@Override
	public void saveUpvote(Upvote upvote) {
		// TODO Auto-generated method stub
		upvoteDao.saveUpvote(upvote);
	}

	@Override
	public List<Upvote> getAllUpvotes() {
		// TODO Auto-generated method stub
		return upvoteDao.getAllUpvotes();
	}

	@Override
	public Upvote getUpvote(int id) {
		return upvoteDao.getUpvote(id);
	}

	@Override
	public void deleteUpvote(int id) {
		upvoteDao.deleteUpvote(id);		
	}

	@Override
	public int countUpvotes(int blogId) {
		return upvoteDao.countUpvotes(blogId);
	}

	@Override
	public boolean checkIfUserHasVotedOnThisBlogYet(int userId, int blogId) {
		return upvoteDao.checkIfUserHasVotedOnThisBlogYet(userId, blogId);
	}

	@Override
	public int getUserUpvoteByUserIdAndBlogId(int userId, int blogId) {
		return upvoteDao.getUserUpvoteByUserIdAndBlogId(userId, blogId);
	}


}
