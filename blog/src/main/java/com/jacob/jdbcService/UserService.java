//@Author: Jacob Duchen

package com.jacob.jdbcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.UserDAO;
import com.jacob.model.User;

@Service("userService")
public class UserService implements UserServiceInterface {

	@Autowired
	private UserDAO userDao;
	
	@Override
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Override
	public User findUserById(int id) {
		return userDao.findUserById(id);
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}
	
	@Override
	 public boolean checkIfEmailIsInDb(String email) {
		return userDao.checkIfEmailIsInDb(email);
	}


}