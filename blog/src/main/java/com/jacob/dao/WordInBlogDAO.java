//@Author=Jacob Duchen

package com.jacob.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.jacob.model.WordInBlog;

@Transactional
@Repository
public class WordInBlogDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WordInBlogDAO(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<WordInBlog> getAllWordsInAllBlogs(){ 
    	String sql = "SELECT id, blog_id, word FROM words_in_blogs";
 	   RowMapper<WordInBlog> rowMapper = new BeanPropertyRowMapper<WordInBlog>(WordInBlog.class);
 	   return this.jdbcTemplate.query(sql, rowMapper);
    }
    
    public List<WordInBlog> getAllWordsInAllBlogsByBlogId(int blog_id){ 
		String sql = "SELECT id, blog_id, word FROM words_in_blogs WHERE blog_id = ?";
    	RowMapper<WordInBlog> rowMapper = new BeanPropertyRowMapper<WordInBlog>(WordInBlog.class);
  	   return this.jdbcTemplate.query(sql, rowMapper, blog_id);
    }
    
    
    public List<WordInBlog> getAllWordsInAllBlogsByWordSearched(String wordSearched){ 
		String sql = "SELECT id, blog_id, word FROM words_in_blogs WHERE word = ?";
    	RowMapper<WordInBlog> rowMapper = new BeanPropertyRowMapper<WordInBlog>(WordInBlog.class);
  	   return this.jdbcTemplate.query(sql, rowMapper, wordSearched);
    }
    
	public WordInBlog getWordInBlogById(int id) {
		String sql = "SELECT id, blog_id, word FROM words_in_blogs WHERE blog_id = ?";
   	 	RowMapper<WordInBlog> rowMapper = new BeanPropertyRowMapper<WordInBlog>(WordInBlog.class);
   	 	WordInBlog wordInBlog = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return wordInBlog; 
	}
	
    public void saveWordInBlogInDb(WordInBlog wordInBlog) {
		String sql = "INSERT INTO words_in_blogs (id, blog_id, word) values (?, ?, ?)";
	   jdbcTemplate.update(sql, wordInBlog.getId(), wordInBlog.getBlog_id(), wordInBlog.getWord());    	
    }
    
	public void deleteWordInBlogFromDb(int id) {
		String sql = "DELETE FROM words_in_blogs WHERE id=?";
    	jdbcTemplate.update(sql, id);
	}
	
	 public int getANewId() {
		 String sql = "SELECT MAX(id) from words_in_blogs";
		 Integer number = jdbcTemplate.queryForObject(sql, Integer.class);
		 return (number + 1); 
	 }

}
