package com.chris.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {
	PASSENGER("passenger"), STAFF("staff");
	
	private final String name;

	UserType(String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return this.name;
	}
}
