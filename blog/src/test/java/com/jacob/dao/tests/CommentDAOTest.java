//@Author: Jacob Duchen
//
//
//package com.jacob.dao.tests;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.jacob.configuration.SecurityConfiguration;
//import com.jacob.dao.CommentDAO;
//import com.jacob.model.Comment;
//
//@RunWith(SpringRunner.class)
//@ComponentScan(basePackages={"com.jacob"})
//@SpringBootTest(classes = { CommentDAO.class, SecurityConfiguration.class })
//@EnableConfigurationProperties
//public class CommentDAOTest {
//	Comment expected;
//	Comment actual;
//	
//	@Autowired
//	private CommentDAO commentDao;
//	
//	@Test
//	public void testGetCommentById() {
//		expected = new Comment(6, 1, 6, "knicks");
//		actual = commentDao.getComment(6);
//		assertEquals(expected.getContent(), actual.getContent());		
//	}
//	
//	@Test
//	public void testPostCommentById() {
//		expected = new Comment(67, 1, 6, "never commented before");
//		commentDao.saveComment(expected);
//		
//		actual = commentDao.getComment(67);
//		assertEquals(expected.getContent(), actual.getContent());
//	}
//
//}
