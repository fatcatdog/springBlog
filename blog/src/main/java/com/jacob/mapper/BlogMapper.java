//@Author: Jacob Duchen
//mapper for our jdbc methods in corresponding Dao

package com.jacob.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.jacob.model.Blog;

final class BlogMapper implements RowMapper<Blog>{
	 
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
