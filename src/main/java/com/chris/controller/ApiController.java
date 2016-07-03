package com.chris.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chris.entity.User;
import com.chris.repository.UserRepository;

@RestController
public class ApiController {

	private final UserRepository userRepository;

	public ApiController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> users() {
		return (List<User>) userRepository.findAll();
	}
}
