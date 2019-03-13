package com.app.ims.auth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserFile {
	String fileName;
	Set<Long> likes = new HashSet<>();
	Map<Long,Comment> comments = new HashMap<>();
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Set<Long> getLikes() {
		return likes;
	}
	public void setLikes(Set<Long> likes) {
		this.likes = likes;
	}
	
}
