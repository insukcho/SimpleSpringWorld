package com.chris.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	private Integer isbn;
	private String title;
	private String author;
	private String description;
	
	public Book() {}

	public Book(Integer isbn, String title, String author, String description) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.description = description;
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}