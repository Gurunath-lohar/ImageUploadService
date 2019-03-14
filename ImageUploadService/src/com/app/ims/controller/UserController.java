package com.app.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ims.auth.AccessLevel;
import com.app.ims.auth.Role;
import com.app.ims.auth.User;
import com.app.ims.service.ImageSearchService;
import com.app.ims.service.UserService;

@RestController
@RequestMapping(value="v0/ims/user")
public class UserController {
	
	
		@Autowired UserService userService;
		
		@Autowired ImageSearchService imageSearchService;
		
		@RequestMapping(method= {RequestMethod.PUT}) 
		public String inviteUser(@RequestParam("userId") Long userId,
				   @RequestParam("invitee") Long invitee, @RequestParam("access") String access) { 
			String message = userService.giveAccess(userId, invitee, AccessLevel.getEnum(access));
		  
		  return message; 
		} 
		
		@RequestMapping(method= {RequestMethod.POST}) 
		public String addUser(@RequestParam("adminId") Long adminId, 
									   @RequestParam("userId") Long userId,
									   @RequestParam("name") String name,
									   @RequestParam("role") String role) {
			User user = new User(Role.getEnum(role), name, userId);
			String message = userService.addUser(adminId, user);		
			
			return message;
		}
		
		@RequestMapping(value="/like", method= {RequestMethod.POST})
		public String addLike(@RequestParam("follower") Long followerId, 
									   @RequestParam("user") Long user,
									   @RequestParam("file") String fileName,@RequestParam("like") Boolean like) {
			String message = userService.addLike(followerId, user, fileName, like);	
			
			return message;
		}

}
