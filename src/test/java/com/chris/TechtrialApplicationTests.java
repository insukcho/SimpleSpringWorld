package com.chris;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.chris.TechtrialApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TechtrialApplication.class)
@WebAppConfiguration
public class TechtrialApplicationTests {

	@Test
	public void contextLoads() {
		return;
	}

}
