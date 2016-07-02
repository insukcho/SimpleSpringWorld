package com.chris.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chris.entity.Book;
import com.chris.repository.BookRepository;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

	private final BookRepository bookRepository;

	public ApiController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@RequestMapping
	public Iterable<Book> books() {
		return bookRepository.findAll();
	}
}
