//@Author: Jacob Duchen


package com.jacob.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.model.Blog;
import com.jacob.model.User;
import com.jacob.jdbcService.BlogService;
import com.jacob.jdbcService.CommentService;
import com.jacob.jdbcService.UpvoteService;
import com.jacob.jdbcService.UserService;

@Controller
@RequestMapping("")
public class UserController {

 @Autowired
 private UserService userService;
 
 @Autowired
 private BlogService blogService;
 
 @Autowired
 private UpvoteService upvoteService;
 
 @Autowired
 private CommentService commentService;
 
 
 @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
 public ModelAndView login() {
  ModelAndView model = new ModelAndView();
  
  model.setViewName("login");
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
 public ModelAndView signup() {
  ModelAndView model = new ModelAndView();
  User user = new User();
  model.addObject("user", user);
  String confirmpassword = "";
  model.addObject("confirmpassword", confirmpassword);
  model.setViewName("signup");
  
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
 public ModelAndView createUser(@Valid User user, String confirmPassword, BindingResult bindingResult) {
	  ModelAndView model = new ModelAndView();
	  
	  User userExists;
	  if(userService.checkIfEmailIsInDb(user.getEmail())) {
		   userExists = userService.findUserByEmail(user.getEmail());		  
	  } else {
		   userExists = null; 
	  }
	  
	   if (user.getEmail().equals("")) {
		  System.out.println("Confirm password empty");
		   model.addObject("msg", "Something went wrong. Please provide an email address. Please try again :)");
		   model.setViewName("signup");
		   return model; 
	  } else if(userExists != null) {
		   System.out.println("email already taken");
		   model.addObject("msg", "Email already taken! Please try again :)");
		   model.setViewName("signup");
		   return model; 
		  }  else if (confirmPassword.equals("")) {
			  System.out.println("email error");
			   model.addObject("msg", "Password does not match confirm password. Please try again :)");
			   model.setViewName("signup");
			   return model; 
		  } else if (!user.getPassword().equals(confirmPassword)){
			  System.out.println("Confirm password empty");
			   model.addObject("msg", "Password does not match confirm password. Please try again :)");
			   model.setViewName("signup");
			   return model; 
		  } else {
		   userService.saveUser(user);	
		   model.setViewName("login");
		   model.addObject("msg", "User has been registered successfully! Please login :)");
		   model.addObject("user", new User());
		   return model; 
		  }	  
	 }
 
 public List<String> getAuthorNamesFromDb(List<Blog> ourBlogs) {
	 List<Integer> authorIds = new ArrayList<Integer>(); 
	 List<String> tempAuthorNames = new ArrayList<String>();
	 
	 for(int i = 0; i < ourBlogs.size(); i++) {
		authorIds.add(ourBlogs.get(i).getAuthor_id());
	 }
	 
	 for(int i = 0; i < ourBlogs.size(); i++) {
		 User tempUser = userService.findUserById(authorIds.get(i));
		 String userFullName = (tempUser.getFirstname() + " " + tempUser.getLastname());
		 
		 if (userFullName.length() == 1) {
			 tempAuthorNames.add("Anonymous");
		 } else {
			 tempAuthorNames.add(userFullName);			 
		 }
	 }
	 return tempAuthorNames; 
 }
 
 public List<String> getBlogTitlesFromBlogList(List<Blog> ourBlogs) {
	 List<String> titles = new ArrayList<String>(); 
	
	 for(int i = 0; i < ourBlogs.size(); i++) {
		 titles.add(ourBlogs.get(i).getTitle());
	 }
	 return titles; 
 }
 
 public List<String> getBlogIdsFromBlogList(List<Blog> ourBlogs) {
	 List<String> blog_ids = new ArrayList<String>(); 
	
	 for(int i = 0; i < ourBlogs.size(); i++) {
		 blog_ids.add(String.valueOf(ourBlogs.get(i).getId()));
	 }
	 
	 return blog_ids; 
 }
 
 public List<List<String>> combineLists(List<String> ids, List<String> authors, List<String> titles){
	 List<List<String>> tempInfo = new ArrayList<List<String>>(); 
	 
	 for(int i = 0; i < ids.size(); i++) {
		 List<String> item = new ArrayList<String>();
		 item.add(ids.get(i));
		 item.add(authors.get(i));
		 item.add(titles.get(i));
		 tempInfo.add(item);
	 }
	 return tempInfo; 
 }
 
 
 
 
 @RequestMapping(value= {"/home"}, method=RequestMethod.GET)
 public ModelAndView home() {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userService.findUserByEmail(auth.getName());
  if((user.getFirstname() + user.getLastname()).length() == 0) {
	  model.addObject("userName", "Anonymous");
  } else {
	  model.addObject("userName", user.getFirstname() + " " + user.getLastname());	  
  }
  
  List<Blog> ourBlogs =  blogService.getAllBlogs(); 
  List<String> ourAuthors = getAuthorNamesFromDb(ourBlogs);
  List<Integer> ourUpvoteCounts = upvoteService.getUpvoteCountsForEachBlogInAList(ourBlogs);
  List<Integer> ourCommentCounts = commentService.getAllCommentsCount(ourBlogs);
  
  model.addObject("upvotes", ourUpvoteCounts); 
  model.addObject("comments", ourCommentCounts); 
  model.addObject("blogs", ourBlogs); 
  model.addObject("authorNames", ourAuthors);
  model.setViewName("home");
  return model;
 }
 
 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
 public ModelAndView accessDenied() {
  ModelAndView model = new ModelAndView();
  model.setViewName("access_denied");
  return model;
 }
}