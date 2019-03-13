package com.app.ims.auth;

public enum Role {
	ADMIN("admin"),USER("user");
	
	String role;
	
	Role(String role) {
		this.role = role;
	}
	
	public static Role getEnum(String role) {
		if(role != null) {
			if(role.equals("admin"))
				return ADMIN;
			else if(role.equals("user"))
				return USER;
		}
		
		return null;
	}
}
