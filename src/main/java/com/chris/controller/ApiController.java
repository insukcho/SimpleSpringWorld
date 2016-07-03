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
	
	@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public User user(@RequestParam("reg_no") Long regNo) {
		return userRepository.findOne(regNo);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public void addUser(@RequestBody Request body) {
		userRepository.save(body.user);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public void deleteUser(@RequestBody String regNo) {
		userRepository.delete(Long.valueOf(regNo));
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public void updateUser(@RequestBody Request body) {
		userRepository.save(body.user);
	}
}
