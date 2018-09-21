//@Author: Jacob Duchen


package com.jacob.dao.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.jacob.configuration.SecurityConfiguration;
import com.jacob.dao.BlogDAO;
import com.jacob.model.Blog;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages={"com.jacob"})
@SpringBootTest(classes = { BlogDAO.class, SecurityConfiguration.class })
@EnableConfigurationProperties

public class BlogDAOTest {
	Blog expected;
	Blog actual;

	@Autowired
	private BlogDAO blogDao;
	
	@Test
	public void testGetBlogDaoGetBlogById() {
		expected = new Blog(20, 40 , "tom", "Green");
		 actual = blogDao.getBlog(20);
		assertEquals(expected.getTitle(), actual.getTitle());		
	}
	
	@Test
	public void testPostBlogDaoGetBlogById() {
		expected = new Blog(15, 40 , "worst", "blog");
		blogDao.saveBlog(expected);
		
		 actual = blogDao.getBlog(15);
		assertEquals(expected.getTitle(), actual.getTitle());				
	}
	
}