//@Author: Jacob Duchen
//mapper for our jdbc methods in corresponding Dao

package com.jacob.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jacob.model.Role;

final class RoleMapper implements RowMapper<Role> {
	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();
		role.setId((rs.getInt("role_id")));
		role.setRole((rs.getString("role")));
		return role;
	}
}
