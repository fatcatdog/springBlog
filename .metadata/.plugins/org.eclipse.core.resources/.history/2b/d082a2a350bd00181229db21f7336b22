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
import com.jacob.dao.UpvoteDAO;
import com.jacob.model.Upvote;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages={"com.jacob"})
@SpringBootTest(classes = { UpvoteDAO.class, SecurityConfiguration.class })
@EnableConfigurationProperties
public class UpvoteDAOTest {
	Upvote expected;
	Upvote actual;
	
	@Autowired
	private UpvoteDAO upvoteDao;
	
	@Test
	public void getUpvote() {
		expected = new Upvote(22, 5, 4);
		actual = upvoteDao.getUpvote(22);
		assertEquals(expected.getBlog_id(), actual.getBlog_id());		
	}
	
	@Test
	public void postUpvote() {
		expected = new Upvote(30, 5, 8);
		upvoteDao.saveUpvote(expected);
		
		actual = upvoteDao.getUpvote(30);
		assertEquals(expected.getBlog_id(), actual.getBlog_id());			
	}
	
}
