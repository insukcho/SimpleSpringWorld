package com.chris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.chris.controller.ApiController;
import com.chris.repository.BookRepository;

@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
@SpringBootApplication
public class TechtrialApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechtrialApplication.class, args);
	}
	
    @Bean
    ApiController apiController(BookRepository bookRepository) {
        return new ApiController(bookRepository);
    }

}
