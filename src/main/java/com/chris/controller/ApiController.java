package com.chris.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chris.entity.User;
import com.chris.repository.UserRepository;
import com.chris.serialization.Request;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ApiController {

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	private final UserRepository userRepository;

	public ApiController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> users() {
		return (List<User>) userRepository.findAll();
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User user(@RequestParam("regNo") Long regNo) {
		return userRepository.findOne(regNo);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public void addUser(@RequestBody Request jsonData) throws Exception {
		userRepository.save(jsonData.user);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public void deleteUser(@RequestParam("regNo") Long regNo) {
		userRepository.delete(regNo);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public void updateUser(@RequestBody Request jsonData) {
		userRepository.save(jsonData.user);
	}
}
