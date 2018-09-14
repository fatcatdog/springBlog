package com.jacob.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.jacob.model.Blog;

final class BlogMapper implements RowMapper<Blog>{

//	 @Id
//	 @GeneratedValue(strategy = GenerationType.AUTO)
//	 private int id;
//	 
//	 @Column(name = "author_id")
//	 private int author_id;
//	 
//	 @Column(name = "title")
//	 private String title; 
//	 
//	 @Column(name = "content")
//	 private String content;
	 
	@Override
	public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
		Blog blog = new Blog();
		
		blog.setId((rs.getInt("id")));
		blog.setAuthor_id((rs.getInt("author_id")));
		blog.setTitle((rs.getString("title")));
		blog.setContent(rs.getString("content"));
		return blog;
	}

}
