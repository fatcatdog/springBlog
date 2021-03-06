//@Author: Jacob Duchen
//mapper for our jdbc methods in corresponding Dao

package com.jacob.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jacob.model.User;


final class UserMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		User user = new User();
		
		user.setId((rs.getInt("id")));
		user.setEmail(rs.getString("email"));
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setPassword(rs.getString("password"));
		user.setActive((rs.getInt("active")));

		return user;
	}

}