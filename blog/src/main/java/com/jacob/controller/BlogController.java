package com.jacob.controller;

import java.util.List;

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
import com.jacob.jdbcService.CommentService;
import com.jacob.jdbcService.UpvoteService;
import com.jacob.jdbcService.UserService;
import com.jacob.model.Blog;
import com.jacob.model.Comment;
import com.jacob.model.User;

@Controller
@RequestMapping()
public class BlogController {

	 @Autowired
	 private BlogService blogService;
	 
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private UpvoteService upvoteService;
	 
	 @Autowired
	 private CommentService commentService;
	 
	 @RequestMapping(value= {"create"}, method=RequestMethod.GET)
	 public ModelAndView createBlog(@Valid User user, BindingResult bindingResult) {
		 
	  ModelAndView model = new ModelAndView();
	  model.addObject("tempBlog", new Blog());
	   model.setViewName("create");
	  
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
		  User tempUser = userService.findUserByEmail(auth.getName());
		  ModelAndView model = new ModelAndView();
		  Blog tempBlog =  blogService.getBlog(id);
		  User tempBlogAuthor = userService.findUserById(tempBlog.getAuthor_id());

		  boolean access = checkIfUserShouldBeAbleToUpdate(tempUser, tempBlog); 
		  
		  if (!access) {
			  System.out.println("Wrong user!!!!");
			  model.setViewName("access_denied");
			  return model; 
		  }

		  model.setViewName("delete");
		  model.addObject("listOfCommentsSize", commentService.getCommentCountForABlog(id));
		  model.addObject("tempUpvoteCount", upvoteService.countUpvotes(id));
		  model.addObject("blogObject", tempBlog);
		  model.addObject("id", id);
		  model.addObject("authorName", tempBlogAuthor.getFirstname() + " " + tempBlogAuthor.getLastname());
		  model.addObject("authorEmail", tempBlogAuthor.getEmail());
		  model.addObject("title", tempBlog.getTitle());
		  model.addObject("content", tempBlog.getContent());
		  
		  return model;
	 }
	 
	 
	 @RequestMapping(value = "/blog/{id}", method=RequestMethod.GET)
	 public ModelAndView viewBlog(@PathVariable(value = "id",  required =false) int id) {
		  ModelAndView model = new ModelAndView();

		  Blog tempBlog =  blogService.getBlog(id);
		  List<String> blogComments = commentService.getContentOfCommentsForABlog(tempBlog.getId());
		 User author = userService.findUserById(tempBlog.getAuthor_id());
		 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  User tempUser = userService.findUserByEmail(auth.getName());
		  boolean access = checkIfUserShouldBeAbleToUpdate(tempUser, tempBlog); 

		  if(blogComments.size() == 0) {
			  model.addObject("commentListEmpty", "Sorry there are no comments as of now :(");
			  model.addObject("listOfCommentsSize", blogComments.size());

		  } else {
			  List<String> commentsAuthors = commentService.getAuthorsOfCommentsForABlog(id);
			  model.addObject("ourCommentAuthors", commentsAuthors);
			  model.addObject("comments", blogComments);
			  model.addObject("listOfCommentsSize", blogComments.size());
		  }
		  
		  model.addObject("currentUserId", tempUser.getId());
		  model.addObject("currentUserEmail", tempUser.getEmail());
		  model.addObject("blogObject", tempBlog);
		  model.addObject("crudRights", access);
		  model.addObject("blogId", tempBlog.getId());
		  
		  if ((author.getFirstname() + author.getLastname()).length() == 0) {
			  model.addObject("authorName", "Anonymous");
		  } else {
			  model.addObject("authorName", author.getFirstname() + " " + author.getLastname());
		  }
		  model.addObject("authorName", author.getFirstname() + " " + author.getLastname());
		  model.addObject("authorEmail", author.getEmail());
		  model.addObject("title", tempBlog.getTitle());
		  model.addObject("content", tempBlog.getContent());
		  model.addObject("tempUpvoteCount", upvoteService.countUpvotes(id));

		  model.setViewName("blog");
		  
		  return model;
	 }
	 
	 @RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
	 public ModelAndView editBlog(@PathVariable(value = "id", required =false) int id) {

		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  User tempAuthor = userService.findUserByEmail(auth.getName());
		  ModelAndView model = new ModelAndView();
		  Blog tempBlog =  blogService.getBlog(id);
		 
		  boolean access = checkIfUserShouldBeAbleToUpdate(tempAuthor, tempBlog); 
		  
		  if (!access) {
			  System.out.println("Not your blog to edit!!!!!");
			  model.setViewName("access_denied");
			  return model; 
		  }
		  
		  model.setViewName("edit");
		  model.addObject("blogId", tempBlog.getId());
		  model.addObject("authorName", tempAuthor.getFirstname() + " " + tempAuthor.getLastname());
		  model.addObject("title", tempBlog.getTitle());
		  model.addObject("content", tempBlog.getContent());
		  model.addObject("tempUpvoteCount", upvoteService.countUpvotes(id));
		  
//		  System.out.println("id: " + tempBlog.getId());
//		  System.out.println("author_id: " + tempBlog.getAuthor_id());
//		  System.out.println("title: " + tempBlog.getTitle());
//		  System.out.println("content: " + tempBlog.getContent());
		  
		  return model; 
		  
	 }
	 
	 @RequestMapping(value="saveBlog", method=RequestMethod.POST)
	 public ModelAndView save(@ModelAttribute("tempBlog") Blog temp) {
		 
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  User tempAuthor = userService.findUserByEmail(auth.getName());
	  int tempAuthorId = tempAuthor.getId();
	  temp.setAuthor_id(tempAuthorId);
	  blogService.saveBlog(temp);
	 
	  return new ModelAndView("redirect:/home");
	 }
	 
	 
	 @RequestMapping(value = "/deleteTheBlog", method=RequestMethod.POST)
	 public ModelAndView deleteTheBlog(@ModelAttribute("id") int id) {
		 
		 blogService.deleteBlog(id);
		 		 
	  return new ModelAndView("redirect:/home");
	 }
	 
	 @RequestMapping(value="updateBlog", method=RequestMethod.POST)
	 public ModelAndView update(@ModelAttribute("tempBlog") Blog temp) {
		  System.out.println("blogController updateBlog Update method called");		  		  
		  blogService.updateBlog(temp);
		  return new ModelAndView("redirect:/home");
	 }
	 
	 
	 
	 
}
