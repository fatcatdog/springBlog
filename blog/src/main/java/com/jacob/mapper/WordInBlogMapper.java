//@Author=Jacob Duchen
 

package com.jacob.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.jacob.model.WordInBlog;

final class WordInBlogMapper implements RowMapper<WordInBlog> {
	@Override
	public WordInBlog mapRow(ResultSet rs, int rowNum) throws SQLException {
		WordInBlog word = new WordInBlog();
		
		word.setId((rs.getInt("id")));
		word.setBlog_id((rs.getInt("blog_id")));
		word.setWord((rs.getString("word")));
		return word;
	}
}
