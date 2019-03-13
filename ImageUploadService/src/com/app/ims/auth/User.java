package com.app.ims.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class User {
	Long id;
	String name;
	Role role;
	Map<AccessLevel,Set<Long>> accessMap;
	Map<String,UserFile> popularityMap = new HashMap<>();
	
	public User(Role role, String name, Long id) {
		this.role = role;
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<AccessLevel, Set<Long>> getAccessMap() {
		return accessMap;
	}

	public void setAccessMap(Map<AccessLevel, Set<Long>> accessMap) {
		this.accessMap = accessMap;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Map<String, UserFile> getPopularityMap() {
		return popularityMap;
	}

	public void setPopularityMap(Map<String, UserFile> popularityMap) {
		this.popularityMap = popularityMap;
	}

	
}
