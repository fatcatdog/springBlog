//@Author: Jacob Duchen

package com.jacob.jdbcService;

import com.jacob.model.User;

public interface UserServiceInterface {
	
	 public User findUserByEmail(String email);
	 
	 public User findUserById(int id);
	 
	 public void saveUser(User user);
	 
	 public boolean checkIfEmailIsInDb(String email);
}
