package com.chris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chris.entity.Book;
import com.chris.repository.BookRepository;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class APIController {

	@Autowired
	protected BookRepository bookRepository;

	@RequestMapping
	public Iterable<Book> books() {
		return bookRepository.findAll();
	}
}
