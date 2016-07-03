package com.chris.helper;

import javax.persistence.AttributeConverter;

import com.chris.model.UserType;

public class UserTypeConverter implements AttributeConverter<UserType, String> {

	@Override
	public String convertToDatabaseColumn(UserType userType) {
		return userType.getName();
	}

	@Override
	public UserType convertToEntityAttribute(String name) {
		UserType userType = null;
		if(UserType.PASSENGER.getName().equals(name)) {
			userType = UserType.PASSENGER;
		} else {
			userType = UserType.STAFF;			
		}
		return userType;
	}

}
