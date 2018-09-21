//@Author=Jacob Duchen
//package com.jacob.dao.tests;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.junit.Assert.assertEquals;
//
//import com.jacob.configuration.SecurityConfiguration;
//import com.jacob.dao.UserDAO;
//import com.jacob.dao.WordInBlogDAO;
//import com.jacob.model.WordInBlog;
//
//@RunWith(SpringRunner.class)
//@ComponentScan(basePackages={"com.jacob"})
//@SpringBootTest(classes = { UserDAO.class, SecurityConfiguration.class })
//@EnableConfigurationProperties
//public class WordInBlogDAOTest {
//
//	WordInBlog expected;
//	WordInBlog actual;
//	
//	@Autowired
//	private WordInBlogDAO wordInBlogDAO;
//	
//	@Test 
//	public void getWordInBlog() {
//		WordInBlog expected = new WordInBlog(1, 59, "Mavericks");
//				
//		WordInBlog actual = wordInBlogDAO.getWordInBlogById(1);
//		assertEquals(expected.getWord(), actual.getWord());		
//	}
//	
//	@Test 
//	public void saveWordInBlog() {
//		WordInBlog expected = new WordInBlog(2, "thisisprobablynotinthisblog");
//		int likelyidofactual = wordInBlogDAO.getANewId();
//
//		wordInBlogDAO.saveWordInBlogInDb(expected); 
//		
//		WordInBlog actual = wordInBlogDAO.getWordInBlogById(likelyidofactual);
//		assertEquals(expected.getWord(), actual.getWord());	
//		wordInBlogDAO.deleteWordInBlogFromDb(likelyidofactual);
//	}
//}
