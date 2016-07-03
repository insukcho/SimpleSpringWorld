package com.chris.model;

public enum UserType {
	PASSENGER("passenger"), STAFF("staff");
	
	private final String name;

	UserType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
