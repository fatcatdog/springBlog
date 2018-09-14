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

import com.jacob.jdbcService.BlogService;
import com.jacob.jdbcService.UpvoteService;
import com.jacob.jdbcService.UserService;
import com.jacob.model.Blog;
import com.jacob.model.User;

@Controller
@RequestMapping("blog")
public class BlogController {

	 @Autowired
	 private BlogService blogService;
	 
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private UpvoteService upvoteService;
	 
	 @RequestMapping(value= {"create"}, method=RequestMethod.GET)
	 public ModelAndView createBlog(@Valid User user, BindingResult bindingResult) {
		 
	  ModelAndView model = new ModelAndView();
	  model.addObject("tempBlog", new Blog());
	   model.setViewName("blog/create");
	  
	  return model;
	 }
	 
	 public boolean checkIfUserShouldBeAbleToUpdate(User user, Blog blog) {
		 if (blog.getAuthor_id() == user.getId()) {
			  return true; 
		  } else {
			  return false;
		  }
	 }
	 
	 @RequestMapping(value = "/delete/{id}", method=RequestMethod.GET)
	 public ModelAndView deleteBlog(@PathVariable(value = "id", required =false) int id) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  User tempAuthor = userService.findUserByEmail(auth.getName());
		  ModelAndView model = new ModelAndView();
		  Blog tempBlog =  blogService.getBlog(id);
		 
		  boolean access = checkIfUserShouldBeAbleToUpdate(tempAuthor, tempBlog); 
		  
		  if (!access) {
			  System.out.println("Wrong user!!!!");
			  model.setViewName("errors/access_denied");
			  return model; 
		  }
		  
		  model.addObject("blogObject", tempBlog);
		  model.addObject("id", tempBlog.getId());
		  model.addObject("authorName", tempAuthor.getFirstname() + " " + tempAuthor.getLastname());
		  model.addObject("authorEmail", tempAuthor.getEmail());
		  model.addObject("title", tempBlog.getTitle());
		  model.addObject("content", tempBlog.getContent());

		  model.setViewName("blog/delete");
		  
		  return model;
	 }
	 
	 @RequestMapping(value = "/deleteTheBlog/{id}", method=RequestMethod.GET)
	 public ModelAndView deleteTheBlog(@PathVariable(value = "id", required =false) int id) {
		 
		 blogService.deleteBlog(id);
		 		 
	  return new ModelAndView("redirect:/home/home");
	 }
	 
	 @RequestMapping(value="/saveBlog", method=RequestMethod.POST)
	 public ModelAndView save(@ModelAttribute("tempBlog") Blog temp) {
		 
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  User tempAuthor = userService.findUserByEmail(auth.getName());
	  int tempAuthorId = tempAuthor.getId();
	  temp.setAuthor_id(tempAuthorId);
	  blogService.saveBlog(temp);
	 
	  return new ModelAndView("redirect:/home/home");
	 }
	 
	 
	 @RequestMapping(value = "{id}", method=RequestMethod.GET)
	 public ModelAndView viewBlog(@PathVariable(value = "id",  required =false) int id) {
		  Blog tempBlog =  blogService.getBlog(id);

		 User author = userService.findUserById(tempBlog.getAuthor_id());
		  ModelAndView model = new ModelAndView();
		  model.addObject("blogObject", tempBlog);

		  model.addObject("blogId", tempBlog.getId());
		  model.addObject("authorName", author.getFirstname() + " " + author.getLastname());
		  model.addObject("authorEmail", author.getEmail());
		  model.addObject("title", tempBlog.getTitle());
		  model.addObject("content", tempBlog.getContent());
		  model.addObject("tempUpvoteCount", upvoteService.countUpvotes(id));

		  model.setViewName("blog/blog");
		  
		  return model;
	 }
	 
	 @RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
	 public ModelAndView updateBlog(@PathVariable(value = "id", required =false) int id) {

		  ModelAndView model = new ModelAndView();

		  model.setViewName("blog/edit");
		  
		  return model;
	 }
	 
	 
	 
	 
}
