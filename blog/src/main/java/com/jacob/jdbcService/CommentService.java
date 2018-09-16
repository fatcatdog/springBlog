package com.jacob.jdbcService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.CommentDAO;
import com.jacob.model.Comment;

@Service("commentService")
public class CommentService  implements CommentServiceInterface  {

	@Autowired
	private CommentDAO commentDao;
	
	@Override
	public Comment getComment(int id) {
		return commentDao.getComment(id);
	}

	@Override
	public void deleteComment(int id) {
		commentDao.deleteComment(id);
	}

	@Override
	public void saveComment(Comment comment) {
		commentDao.saveComment(comment);
	}

	@Override
	public List<Comment> getAllCommentsForABlog(int id) {
		return commentDao.getAllCommentsForABlog(id);
	}

	@Override
	public int getANewId() {
		return commentDao.getANewId();
	}

}
