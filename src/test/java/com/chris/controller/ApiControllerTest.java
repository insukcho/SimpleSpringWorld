package com.chris.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StreamUtils;

import com.chris.entity.User;
import com.chris.repository.FakeUserRepository;
import com.chris.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ApiControllerTest {
	private MockMvc subject;

	UserRepository userRepository = new FakeUserRepository();
	
	@Before
	public void setUp() throws Exception {
		subject = MockMvcBuilders.standaloneSetup(new ApiController(userRepository)).build();
	}

	@Test
	public void getUsers() throws Exception {
		userRepository.deleteAll();
		
		userRepository.save(new User(11L, "wara",  "1234", "Ryan White", "2010.11.02", "wara@airline.com", "I love SPRING!!", "Staff"));
		userRepository.save(new User(12L, "napal",  "4321", "Chris Cho", "1980.04.05", "napal@bbb.com", "You love SPRING!!", "Passenger"));
		
		ResultActions responseJson = subject.perform(get("/users").accept(MediaType.APPLICATION_JSON_VALUE));
		responseJson.andExpect(status().isOk()).andExpect(content().json(getJsonContent("response.json")));
	}
	
	@Test
	public void getUser() throws Exception {
		userRepository.save((new User(1L, "chircho",  "q1w2e3", "Chris Cho", "1980.06.05", "isi.cho@gmail.com", "He love SPRING!!", "Passenger")));
		
		ResultActions responseJson = subject.perform(get("/user").param("regNo", "1").accept(MediaType.APPLICATION_JSON_VALUE));
		responseJson.andExpect(status().isOk()).andExpect(content().json(getJsonContent("response_for_one.json")));
	}

	@Test
	public void addUser() throws Exception {
		String body = "{\"regNo\": 121, \"id\": \"wara\",  \"pwd\": \"1234\", \"name\": \"Ryan White\", \"birthday\": \"2010.11.02\", \"email\": \"wara@airline.com\", \"description\": \"I love SPRING!!\", \"userType\": \"Staff\"}";
		subject.perform(put("/user").content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteUser() throws Exception {
		subject.perform(delete("/user").param("regNo", "121").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void updateUser() throws Exception {
		String body = "{\"regNo\": 121, \"id\": \"wara99\",  \"pwd\": \"9999\"}";
		subject.perform(post("/user").content(body).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	private String getJsonContent(String path) throws IOException {
		return StreamUtils.copyToString(getClass().getClassLoader().getResourceAsStream(path),
				Charset.defaultCharset());
	}
}
