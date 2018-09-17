//@Author: Jacob Duchen
//Comments don't currently have CRUD functionality. So just one simple save method.

package com.jacob.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.jdbcService.CommentService;
import com.jacob.jdbcService.UserService;
import com.jacob.model.Comment;
import com.jacob.model.User;

@Controller
@RequestMapping()
public class CommentController {
	
	 @Autowired
	 private CommentService commentService;

	 @Autowired
	 private UserService userService;
	 
	 
	 @RequestMapping(value="saveComment/{id}", method=RequestMethod.POST)
	 public ModelAndView save(@PathVariable(value = "id", required =false) int id, @ModelAttribute("tempComment") Comment comment) {
		 System.out.println("savecomment called in comment controller");
	  
		 //getting auth information 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 User tempAuthor = userService.findUserByEmail(auth.getName());

		 //saving comment 
		 comment.setId(commentService.getANewId());
		 comment.setAuthor_id(tempAuthor.getId());
		 comment.setBlog_id(id);
		 commentService.saveComment(comment);
			 
	  return new ModelAndView("redirect:/blog/" + id);
	  
	 }
}
