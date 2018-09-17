package com.jacob.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jacob.model.Blog;

@Transactional
@Repository
public class BlogDAO {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public BlogDAO(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }

    public List<Blog> getAllBlogs() {
    	String sql = "SELECT blog.id, blog.author_id, blog.title, blog.content FROM blog LEFT JOIN upvote ON blog.id = upvote.blog_id GROUP BY blog.id ORDER BY COUNT(blog.id) DESC";
	   RowMapper<Blog> rowMapper = new BeanPropertyRowMapper<Blog>(Blog.class);
	   return this.jdbcTemplate.query(sql, rowMapper);
    }
    
    public void saveBlog(Blog blog) {
	   String sql = "INSERT INTO blog (id, author_id, title, content) values (?, ?, ?, ?)";
	   jdbcTemplate.update(sql, blog.getId(), blog.getAuthor_id(), blog.getTitle(), blog.getContent());    	
    }
    
    public Blog getBlog(int id) {
    	String sql = "SELECT id, author_id, title, content FROM blog WHERE id = ?";
    	 RowMapper<Blog> rowMapper = new BeanPropertyRowMapper<Blog>(Blog.class);
		 Blog blog = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return blog; 
    }
    
    public void updateBlog(Blog blog) {
        String sql = "UPDATE blog SET title=?, content=? WHERE id=?";
        jdbcTemplate.update(sql, blog.getTitle(), blog.getContent(), blog.getId());
    } 
    
    public void deleteBlog(long id) {
    	String sql = "DELETE FROM blog WHERE id=?";
    	jdbcTemplate.update(sql, id);
    }
	
}
