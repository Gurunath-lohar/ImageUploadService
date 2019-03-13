package com.app.ims.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.app.ims.auth.AccessLevel;
import com.app.ims.auth.Role;
import com.app.ims.auth.User;
import com.app.ims.auth.UserFile;
import com.app.ims.response.ImsResponse;

@Component
public class UserService {
	private Map<Long, User> userStore = new HashMap<Long, User>();
	
	public UserService() {
		userStore.put(123124235L, getAdmin());
	}
	
	private User getAdmin() {
		User user = new User(Role.ADMIN, "John", 123124235L );
		
		return user;
	}
	
	public String addUser(Long adminId, User user) {
		String message;
		if(userStore.get(adminId) != null && userStore.get(adminId).getRole() == Role.ADMIN) {
			userStore.put(user.getId(), user);
			message = "Success";
		}
		else {
			message = "Insufficient right to add user";
		}
		
		return message;
	}
	
	public String giveAccess(Long user, Long invitee, AccessLevel accessLevel) {
		String message = null;
		if(userStore.containsKey(user) && userStore.containsKey(invitee)) {
			if(userStore.get(invitee).getAccessMap() != null &&
					!userStore.get(invitee).getAccessMap().isEmpty()) {
				if(userStore.get(invitee).getAccessMap().get(accessLevel) != null &&
						!userStore.get(invitee).getAccessMap().get(accessLevel).isEmpty()) {
					userStore.get(invitee).getAccessMap().get(accessLevel).add(user);
				}
				else {
					Set<Long> users = new HashSet<Long>();
					users.add(user);
					userStore.get(invitee).getAccessMap().put(accessLevel, users);
				}
			}
			else {
				Map<AccessLevel,Set<Long>> accessMap = new HashMap<>();
				Set<Long> users = new HashSet<Long>();
				users.add(user);
				accessMap.put(accessLevel, users);
				userStore.get(invitee).setAccessMap(accessMap);
			}
			message = "success";
		}
		else {
			if(!userStore.containsKey(user)) {
				message = "user not found";
			}
			else if(!userStore.containsKey(invitee)) {
				message = "invitee not found";
			}
		}
		
		
		return message;
	}
	
	public User getUser(Long id) {
		return userStore.get(id);
	}
	
	public String addLike(Long followerId, Long user, String fileName, Boolean like) {
		String message = null;
		if(userStore.containsKey(followerId) && userStore.containsKey(user)) {
			if(userStore.get(user).getPopularityMap().get(fileName) != null) {
				if(like) {
					userStore.get(user).getPopularityMap().get(fileName).getLikes().add(followerId);
				}
				else {
					userStore.get(user).getPopularityMap().get(fileName).getLikes().remove(followerId);
				}
				message = "success";
			}
		}
		else {
			message = "user not found";
		}
		
		return message;
	}
	
	public void addUserFile(String user, ImsResponse imsResponse) {
		UserFile userFile = new UserFile();
		String fileName = imsResponse.getImageResource().get(0).getFileName();
		userFile.setFileName(fileName);
		userStore.get(getUserId(user)).getPopularityMap().put(fileName, userFile);
	}
	
	private Long getUserId(String user) {
		for(User userObject : userStore.values()) {
			if(userObject.getName().equals(user)) {
				return userObject.getId();
			}
		}
		
		return null;
	}
}
