package com.chris.helper;

import javax.persistence.AttributeConverter;

import org.springframework.beans.factory.annotation.Autowired;

import com.chris.entity.User;
import com.chris.repository.UserRepository;

public class UserConverter implements AttributeConverter<User, Long> {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Long convertToDatabaseColumn(User user) {
		return user.getRegNo();
	}

	@Override
	public User convertToEntityAttribute(Long regNo) {
		return userRepository.findOne(regNo); 
	}

}
