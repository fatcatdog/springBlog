//@Author: Jacob Duchen
//pretty straight forward stuff here 
//i hope to put more of the messy logic in our controllers in here 

package com.jacob.jdbcService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.CommentDAO;
import com.jacob.model.Blog;
import com.jacob.model.Comment;
import com.jacob.model.User;

@Service("commentService")
public class CommentService  implements CommentServiceInterface  {

	@Autowired
	private CommentDAO commentDao;

	@Autowired
	private UserService userService;

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
	public List<Integer> getAllCommentsCount(List<Blog> blogs) {
		List<Integer> ourCommentsCount = new ArrayList<Integer>();

		for(int i = 0; i < blogs.size(); i++) {

			int num = commentDao.getAllCommentsForABlog(blogs.get(i).getId()).size();
			ourCommentsCount.add(num);		
		}
		return ourCommentsCount;
	}


	@Override
	public List<Comment> getAllCommentsForABlog(int id) {
		return commentDao.getAllCommentsForABlog(id);
	}

	@Override
	public int getANewId() {
		return commentDao.getANewId();
	}

	@Override
	public User getAuthorFromComment(int id) {
		return userService.findUserById(id);
	}

	@Override
	public List<String> getAuthorsOfCommentsForABlog(int id){
		List<String> ourListOfCommentAuthors = new ArrayList<String>();

		List<Comment> ourComments =  getAllCommentsForABlog(id);

		for(int i = 0; i < ourComments.size(); i++) {
			String tempName = userService.findUserById(ourComments.get(i).getAuthor_id()).getFirstname() + " " + userService.findUserById(ourComments.get(i).getAuthor_id()).getLastname();

			if (tempName.length() == 1) {
				ourListOfCommentAuthors.add("Anonymous");

			} else {
				ourListOfCommentAuthors.add(tempName);
			}
		}

		return ourListOfCommentAuthors;
	}

	@Override
	public List<String> getContentOfCommentsForABlog(int id){
		List<String> ourListOfCommentOfBlog = new ArrayList<String>();

		List<Comment> ourComments =  getAllCommentsForABlog(id);

		for(int i = 0; i < ourComments.size(); i++) {
			ourListOfCommentOfBlog.add(ourComments.get(i).getContent());
		}

		return ourListOfCommentOfBlog;
	}

	@Override
	public int getCommentCountForABlog(int blogId) {
		int total = getAllCommentsForABlog(blogId).size();

		return total;
	}


}
