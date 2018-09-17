//@Author: Jacob Duchen
//Blog controller does have CRUD functionality. I am not super happy that there is so much logic in our controller methods and that all of the services are pulled in here. I think it would be better to keep the services talking to eachother. 

package com.jacob.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
	 
	 private User getCurrentAuthUser() {
		  //this variable will be used to get current user Authentication(where we can get there user id from) from spring security 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		 //getting user object from spring security Authentication object 
		  User tempUser = userService.findUserByEmail(auth.getName());
		 return tempUser;
	 }

	 //this method provides user the view to write a blog
	 @RequestMapping(value= {"create"}, method=RequestMethod.GET)
	 public ModelAndView createBlog(@Valid User user) {

	  ModelAndView model = new ModelAndView();
	  model.addObject("tempBlog", new Blog());
	  model.setViewName("create");

	  return model;
	 }

	 //this method checks if current user.id is the author_id of the blog user is attempting to edit. If user is the author of blog, they can edit, we return true; otherwise we return false. 
	 public boolean checkIfUserShouldBeAbleToUpdate(User user, Blog blog) {
		 if (blog.getAuthor_id() == user.getId()) {
			  return true;
		  } else {
			  return false;
		  }
	 }

	 //here is our delete blog action. We really dont delete the blog here. But we have some logic to allow user to get to delete page, or provide them error page. We really should prevent not-correct user from even seeing the delete and edit pages.
	 @RequestMapping(value = "/delete/{id}", method=RequestMethod.GET)
	 public ModelAndView deleteBlog(@PathVariable(value = "id", required =false) int id) {
		
		 //getting authorized current user object
		 User tempUser = getCurrentAuthUser();
		 
		  ModelAndView model = new ModelAndView();
		  
		  //get the blog that user wants to delete!
		  Blog tempBlog =  blogService.getBlog(id);
		  
		  //get author of that blog
		  User tempBlogAuthor = userService.findUserById(tempBlog.getAuthor_id());

		  //lets use that user.id = blog.author.id method we mentioned previously 
		  boolean access = checkIfUserShouldBeAbleToUpdate(tempUser, tempBlog);
		  if (!access) {
			  System.out.println("Wrong user!!!!");
			  //we return error page to user trying to delete blog they shouldnt have rights too
			  model.setViewName("access_denied");
			  return model;
		  }
		  
		  //get our delete jsp which has the action attached to it where user can actually delete a blog
		  model.setViewName("delete");
		  
		  //get amount of comments to display to user 
		  model.addObject("listOfCommentsSize", commentService.getCommentCountForABlog(id));
		  
		  //get amount of upvotes to display to user 
		  model.addObject("tempUpvoteCount", upvoteService.countUpvotes(id));
		  
		  //get blogObject to pass to user. We really are passing duplicate data here that can be refactored in the future. 
		  model.addObject("blogObject", tempBlog);
		  
		  //id of blog in question 
		  model.addObject("id", id);
		  
		  //displaying strings of author names on the page
		  model.addObject("authorName", tempBlogAuthor.getFirstname() + " " + tempBlogAuthor.getLastname());
		  
		  //displaying string of email for the page
		  model.addObject("authorEmail", tempBlogAuthor.getEmail());
		  
		  //displaying string of title of blog for the page
		  model.addObject("title", tempBlog.getTitle());
		  
		  //displaying string of content of blog for the page
		  model.addObject("content", tempBlog.getContent());

		  //returning the modelView which holds all this information, which the delete.jsp will consume
		  return model;
	 }

	 //this view blog has a lot of logic, and repeated data, that needs to be refactored
	 @RequestMapping(value = "/blog/{id}", method=RequestMethod.GET)
	 public ModelAndView viewBlog(@PathVariable(value = "id",  required =false) int id) {
		  ModelAndView model = new ModelAndView();
		  
		  //getting blog object from path variable int id so we can pull out all relevant information to display on the jsp
		  Blog tempBlog =  blogService.getBlog(id);
		  
		  //getting all of our comments 
		  List<String> blogComments = commentService.getContentOfCommentsForABlog(tempBlog.getId());
		  
		  //getting author name from blog.author_id to display on page 
		 User authorOfBlog = userService.findUserById(tempBlog.getAuthor_id());

		 
		//getting authorized current user object
		 User tempUser = getCurrentAuthUser();
		 
		 //check if we should have rights to edit blog
		  boolean access = checkIfUserShouldBeAbleToUpdate(tempUser, tempBlog);

		  //if blog has no comments we display on view No comments (and we dont include the object that is supposed to contain comment author names
		  if(blogComments.size() == 0) {
			  model.addObject("commentListEmpty", "Sorry there are no comments as of now :(");
			  //this might as well just be int 0, but i user the variable blogComments.size()
			  model.addObject("listOfCommentsSize", blogComments.size());

		  } else {
			  //we have comments, so we provide information to view that will display amount of comments on blog, author name for each comment, and comment content for each comment
			  List<String> commentsAuthors = commentService.getAuthorsOfCommentsForABlog(id);
			  model.addObject("ourCommentAuthors", commentsAuthors);
			  model.addObject("comments", blogComments);
			  model.addObject("listOfCommentsSize", blogComments.size());
		  }
		  
		  //we dont use any validation on name inputs, so user name could be "", so if so, we provide Anonymous to view 
		  if ((authorOfBlog.getFirstname() + authorOfBlog.getLastname()).length() == 0) {
			  model.addObject("authorName", "Anonymous");
		  } else {
			  //provide name if its not an empty string
			  model.addObject("authorName", authorOfBlog.getFirstname() + " " + authorOfBlog.getLastname());
		  }
		  
		  	//more information needed for view blog view
		  
			  model.addObject("currentUserId", tempUser.getId());
			  model.addObject("currentUserEmail", tempUser.getEmail());
			  model.addObject("blogObject", tempBlog);
			  model.addObject("crudRights", access);
			  model.addObject("blogId", tempBlog.getId());
		  
			  model.addObject("authorName", authorOfBlog.getFirstname() + " " + authorOfBlog.getLastname());
			  model.addObject("authorEmail", authorOfBlog.getEmail());
			  model.addObject("title", tempBlog.getTitle());
			  model.addObject("content", tempBlog.getContent());
			  model.addObject("tempUpvoteCount", upvoteService.countUpvotes(id));
	
			  //setting blog.jsp as view to view blog
			  model.setViewName("blog");
	
			  return model;
	 }

	 @RequestMapping(value="saveBlog", method=RequestMethod.POST)
	 public ModelAndView save(@ModelAttribute("tempBlog") Blog temp) {

		//getting authorized current user object
		 User tempUser = getCurrentAuthUser();
		 
		  int tempAuthorId = tempUser.getId();
		  temp.setAuthor_id(tempAuthorId);
		  blogService.saveBlog(temp);

		  //after user saves blog, we send them to website homepage
		  return new ModelAndView("redirect:/home");
	 }


	 //user hopefully wont see this page if they cant get to the delete view. Hopefully... Additional security logic anyway though. 
	 @RequestMapping(value = "/deleteTheBlog", method=RequestMethod.POST)
	 public ModelAndView deleteTheBlog(@ModelAttribute("id") int id) {
		//getting authorized current user object
		 User tempUser = getCurrentAuthUser();
		 
		 //current blog that user attempting to be deleted 
		 Blog tempBlog = blogService.getBlog(id);
		 
		 //if user matches blog author we will delete it
		 if(tempUser.getId() == tempBlog.getAuthor_id()) {
			 blogService.deleteBlog(id);
			  return new ModelAndView("redirect:/home");
		 } else {
			  ModelAndView model = new ModelAndView();
			 System.out.println("Not your blog to edit!!!!!");
			  model.setViewName("access_denied");
			  return model;
		 }
	 }


	 //there is a lot of code in this view that is duplicated in our view blog method. Definitly could use some refactoring. 
	 @RequestMapping(value = "/edit/{id}", method=RequestMethod.GET)
	 public ModelAndView editBlog(@PathVariable(value = "id", required =false) int id) {

		  User tempAuthor = getCurrentAuthUser();
		  ModelAndView model = new ModelAndView();
		  Blog tempBlog =  blogService.getBlog(id);

		  boolean access = checkIfUserShouldBeAbleToUpdate(tempAuthor, tempBlog);

		  //if user doesn't match author of blog we redirect to error page
		  if (!access) {
			  System.out.println("Not your blog to edit!!!!!");
			  model.setViewName("access_denied");
			  return model;
		  }

		  //otherwise, we set edit.jsp and add all of our needed information to edit view 
		  model.setViewName("edit");
		  model.addObject("blogId", id);
		  model.addObject("authorName", tempAuthor.getFirstname() + " " + tempAuthor.getLastname());
		  model.addObject("title", tempBlog.getTitle());
		  model.addObject("content", tempBlog.getContent());
		  model.addObject("tempUpvoteCount", upvoteService.countUpvotes(id));

		  return model;

	 }
	 
////	 a bad user hopefully wont see this action because they shouldnt be able to get to edit page. Hopefully... Additional security logic anyway though. 
//i need to change the logic here so if no new title or new content is provided the updateblog actions saves empty string, bad
	 
	 //	 @RequestMapping(value="updateBlog", method=RequestMethod.POST)
//	 public ModelAndView update(@ModelAttribute("tempBlog") Blog temp) {
//		 //our auth logic that is being repeated in every method which i hope to refactor
////		 User tempUser = getCurrentAuthUser();
////		 
////		 //if user is updating not there own own blow
////		 if(tempUser.getId() == tempBlog.getAuthor_id()) {
//		 	Blog preUpdatedBlog = blogService.getBlog(temp.getId());
//		 	
//		 	//if user fails to provide new title we just keep old content 
//		 	System.out.println("tempBlog.getTitle()");
//		 	System.out.println(temp.getTitle());
//		 	System.out.println("tempBlog.getContent()");
//		 	System.out.println(temp.getContent());
//		 	System.out.println(temp.getId());
//
//		 	System.out.println("preUpdatedBlog.getTitle()");
//		 	System.out.println(preUpdatedBlog.getTitle());
//		 	System.out.println("preUpdatedBlog.getContent()");
//		 	System.out.println(preUpdatedBlog.getContent());
//		 	System.out.println(preUpdatedBlog.getId());
//
////		 	if (tempBlog.getTitle().length()==0) {
////		 		tempBlog.setContent(preUpdatedBlog.getTitle());
////		 	}
////		 	
////		 	//if user fails to provide new content we just keep old content 
////		 	if (tempBlog.getContent().length()==0) {
////		 		tempBlog.setContent(preUpdatedBlog.getContent());
////		 	}
//		 
//			 System.out.println("blogController updateBlog Update method called");
//			  blogService.updateBlog(temp);
//			  return new ModelAndView("redirect:/home"); 
////		 } else {
////			ModelAndView model = new ModelAndView();
////			System.out.println("Not your blog to edit!!!!!");
////			model.setViewName("access_denied");
////			return model;
////		 }
//	 }
	 
	 //still working on this
	 @RequestMapping(value="updateBlog", method=RequestMethod.POST)
	 public ModelAndView update(@ModelAttribute("tempBlog") Blog temp) {
		  System.out.println("blogController updateBlog Update method called");
		  System.out.println(temp.getTitle());
		  System.out.println(temp.getContent());
		  System.out.println(temp.getId());

		  blogService.updateBlog(temp);
		  return new ModelAndView("redirect:/home");
	 }



}
