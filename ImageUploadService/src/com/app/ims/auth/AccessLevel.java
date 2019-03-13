package com.app.ims.auth;

public enum AccessLevel {
	ALL("all"),READ("read"),READ_WRITE("read_write"),READ_WRITE_UPDATE("read_write_update"),DELETE("delete");
	
	String access;
	AccessLevel(String access) {
		this.access = access;
	}
	
	public static AccessLevel getEnum(String accessString) {
		if(accessString != null) {
			if(accessString.equals("all"))
				return ALL;
			else if(accessString.equals("read"))
				return READ;
			else if(accessString.equals("read_write"))
				return READ_WRITE;
			else if(accessString.equals("read_write_update"))
				return READ_WRITE_UPDATE;
			else if(accessString.equals("delete"))
				return DELETE;
		}
		
		return null;
	}
}
