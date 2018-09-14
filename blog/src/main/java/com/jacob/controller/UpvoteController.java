package com.jacob.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.model.Blog;
import com.jacob.model.Upvote;
import com.jacob.model.User;
import com.jacob.repository.BlogRepository;
import com.jacob.repository.UpvoteRepository;
import com.jacob.jdbcService.BlogService;
import com.jacob.jdbcService.UpvoteService;
import com.jacob.jdbcService.UserService;

@Controller
@RequestMapping("vote")
public class UpvoteController {
	 @Autowired
	 private BlogService blogService;
	 
	 @Autowired
	 private UserService userService;
	 
	 @Autowired 
	 private UpvoteService upvoteService; 
	 
//	 @Autowired
//    private UpvoteRepository upvoteRepository;
	 
	 @RequestMapping(value = "up/{id}", method=RequestMethod.GET)
	 public ModelAndView createUpvote(@PathVariable(value = "id",  required =false) int id) {
		 
		 System.out.println("createUpvote method in blog controller has been called");

		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  User tempUser = userService.findUserByEmail(auth.getName());
		  
		  ModelAndView model = new ModelAndView();
		  
		  Blog tempBlog =  blogService.getBlog(id);
	
		  User author = userService.findUserById(tempBlog.getAuthor_id());
		 
		  model.addObject("blogObject", tempBlog);

		  model.addObject("blogId", tempBlog.getId());
		  model.addObject("authorName", author.getFirstname() + " " + author.getLastname());
		  model.addObject("authorEmail", author.getEmail());
		  model.addObject("title", tempBlog.getTitle());
		  model.addObject("content", tempBlog.getContent());
	  
		  if(!upvoteService.checkIfUserHasVotedOnThisBlogYet(tempUser.getId(), id)) {
			  Upvote tempUpvote = new Upvote(); 
			  tempUpvote.setAuthor_id(tempUser.getId());
			  tempUpvote.setBlog_id(id);
			
			  upvoteService.saveUpvote(tempUpvote);
			  model.addObject("tempUpvoteCount", upvoteService.countUpvotes(id));			  
		  } else {
			  model.addObject("tempUpvoteCount", upvoteService.countUpvotes(id));			  
		  }

		  
	  model.setViewName("blog/blog");
	  return model;
	 }
	 
	 @RequestMapping(value = "down/{id}", method=RequestMethod.GET)
	 public ModelAndView createDownVote(@PathVariable(value = "id",  required =false) int id) {
		 
		 System.out.println("createDownVote method in blog controller has been called");

		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  User tempUser = userService.findUserByEmail(auth.getName());
		  
		  ModelAndView model = new ModelAndView();
		  
		  Blog tempBlog =  blogService.getBlog(id);
	
		  User author = userService.findUserById(tempBlog.getAuthor_id());
		 
		  model.addObject("blogObject", tempBlog);

		  model.addObject("blogId", tempBlog.getId());
		  model.addObject("authorName", author.getFirstname() + " " + author.getLastname());
		  model.addObject("authorEmail", author.getEmail());
		  model.addObject("title", tempBlog.getTitle());
		  model.addObject("content", tempBlog.getContent());
	  
		  if(upvoteService.checkIfUserHasVotedOnThisBlogYet(tempUser.getId(), id)) {

			int ourIdToDelete = upvoteService.getUserUpvoteByUserIdAndBlogId(tempUser.getId(), tempBlog.getId());
			
			upvoteService.deleteUpvote(ourIdToDelete);		  
		  } 
		  
		  model.addObject("tempUpvoteCount", upvoteService.countUpvotes(id));

		  
	  model.setViewName("blog/blog");
	  return model;
	 }
	 
	 
	 
//	 Upvote tempUpvote = new Upvote();
//	  tempUpvote.setAuthor_id(tempAuthorId);
//	  tempUpvote.setBlog_id(temp.getId());
//	  
//	  upvoteService.saveUpvote(tempUpvote);
	  
	
}
