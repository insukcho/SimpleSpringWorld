package com.chris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
@SpringBootApplication
public class TechtrialApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechtrialApplication.class, args);
	}
}
