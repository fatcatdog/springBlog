//@Author: Jacob Duchen
//upvote controller does have CRUD functionality 

package com.jacob.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.model.Blog;
import com.jacob.model.Upvote;
import com.jacob.model.User;

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
	 
	 //getting current user to check if user has voted on blog in question yet or not 
	 private User getCurrentAuthUser() {
		  //this variable will be used to get current user Authentication(where we can get there user id from) from spring security 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		 //getting user object from spring security Authentication object 
		  User tempUser = userService.findUserByEmail(auth.getName());
		 return tempUser;
	 }

	 //upvote method
	 @RequestMapping(value = "up/{id}", method=RequestMethod.GET)
	 public ModelAndView createUpvote(@PathVariable(value = "id",  required =false) int id) {
		 
		  User tempUser = getCurrentAuthUser();
		 
	  
		  //if user has not already upvoted on blog, we save upvote 
		  if(!upvoteService.checkIfUserHasVotedOnThisBlogYet(tempUser.getId(), id)) {
			  Upvote tempUpvote = new Upvote(); 
			  tempUpvote.setAuthor_id(tempUser.getId());
			  tempUpvote.setBlog_id(id);
			
			  upvoteService.saveUpvote(tempUpvote);
		  } 
		  
		  //returning view
		  return new ModelAndView("redirect:/blog/" + id);
	 }
	 
	 //downvote method 
	 @RequestMapping(value = "down/{id}", method=RequestMethod.GET)
	 public ModelAndView createDownVote(@PathVariable(value = "id",  required =false) int id) {
		 
		 //getting current user from Spring Security 
		  User tempUser = getCurrentAuthUser();
		  
		  Blog tempBlog =  blogService.getBlog(id);

		  //if user has an upvote on this blog, we allow the user to delete upvote
		  if(upvoteService.checkIfUserHasVotedOnThisBlogYet(tempUser.getId(), id)) {

			int ourIdToDelete = upvoteService.getUserUpvoteByUserIdAndBlogId(tempUser.getId(), tempBlog.getId());
			
			upvoteService.deleteUpvote(ourIdToDelete);		  
		  } 
	  
	  return new ModelAndView("redirect:/blog/" + id);
	 }
	
}
