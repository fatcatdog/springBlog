//@Author: Jacob Duchen

package com.jacob.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jacob.model.Comment;

@Transactional
@Repository
public class CommentDAO {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public CommentDAO(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
    
	public Comment getComment(int id) {
		String sql = "SELECT id, author_id, blog_id, content FROM comments WHERE id = ?";
   	 	RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
   	 	Comment comment = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return comment; 
	}

	public void deleteComment(int id) {
		String sql = "DELETE FROM comments WHERE id=?";
    	jdbcTemplate.update(sql, id);
	}
	
    public void saveComment(Comment comment) {
		String sql = "INSERT INTO comments (id, author_id, blog_id, content) values (?, ?, ?, ?)";
		int tempCommentId = getANewId();
	   jdbcTemplate.update(sql, tempCommentId, comment.getAuthor_id(), comment.getBlog_id(), comment.getContent());  
	  
    }
    
	public List<Comment> getAllCommentsForABlog(int id){
		String sql = "SELECT id, author_id, blog_id, content FROM comments WHERE blog_id = ?";
    	RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
  	   return this.jdbcTemplate.query(sql, rowMapper, id);
	}
	
	public List<Comment> getAllComments(){
		String sql = "SELECT id, author_id, blog_id, content FROM comments";
    	RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
  	   return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	 public int getANewId() {
		 String sql = "SELECT MAX(id) from comments";
		 Integer number = jdbcTemplate.queryForObject(sql, Integer.class);
		 return (number + 1); 
	 }
    
}
