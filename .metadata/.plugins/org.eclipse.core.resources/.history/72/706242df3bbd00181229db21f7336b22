//@Author: Jacob Duchen


package com.jacob.dao.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.jacob.configuration.SecurityConfiguration;
import com.jacob.dao.UserDAO;

import com.jacob.model.User;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages={"com.jacob"})
@SpringBootTest(classes = { UserDAO.class, SecurityConfiguration.class })
@EnableConfigurationProperties
public class UserDAOTest {
	User expected;
	User actual;
	
	@Autowired
	private UserDAO userDAO;
	
	@Test 
	public void testGetAUser() {
		expected = new User(1, "duchenjacob@gmail.com", "jacob", "duchen", "password");
		actual = userDAO.findUserByEmail("duchenjacob@gmail.com");
		assertEquals(expected.getLastname(), actual.getLastname());		
	}
	
	
	@Test 
	public void testSaveAUser() {
		expected = new User(62, "bademail", "test", "test", "test", 1);
		userDAO.saveUser(expected);
		actual = userDAO.findUserByEmail("bademail");
		
		assertEquals(expected.getEmail(), actual.getEmail());		

		}
}
