package com.chris.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.chris.repository.BookRepository;
import com.chris.repository.FakeBookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ApiControllerTest {
	private MockMvc subject;
	
	BookRepository bookRepository = new FakeBookRepository();

	@Before
	public void setUp() throws Exception {
		subject = MockMvcBuilders.standaloneSetup(new ApiController(bookRepository)).build();
	}

	@Test
	public void getData() throws IOException, Exception {
		ResultActions responseJson = subject.perform(get("/books").accept(MediaType.APPLICATION_JSON_VALUE));
		responseJson.andExpect(status().isOk()).andExpect(content().json(getJsonContent("response.json")));
	}

	private String getJsonContent(String path) throws IOException {
		return StreamUtils.copyToString(getClass().getClassLoader().getResourceAsStream(path),
				Charset.defaultCharset());
	}
}
